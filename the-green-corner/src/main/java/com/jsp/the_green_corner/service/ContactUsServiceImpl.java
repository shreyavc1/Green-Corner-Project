package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.ContactUs;
import com.jsp.the_green_corner.exception.DatabaseException;
import com.jsp.the_green_corner.repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ContactUsRepository contactUsRepository;

    @Value("${app.admin.mail}")
    private String adminMail;

    @Transactional
    public String saveAndSendEmailToAdminAndAutoReplyPlusManualReply(ContactUs contactUs){
        try{
            contactUsRepository.save(contactUs);
            if(sendEmailToAdminAndSetManualReply(contactUs)) {
                sendAutoReplyToClient(contactUs);
            }
        }
        catch(DataAccessException ex){
            throw new DatabaseException("Database error occurred while saving complaint"+
            " details(contactUs) of " + contactUs.getName() +" in database." );
        }
        return contactUs.getName() + "'s mail successfully sent to admin and complaint details saved to " +
                "database successfully";
    }

    private boolean sendEmailToAdminAndSetManualReply(ContactUs contactUs){

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(adminMail);//to
            message.setSubject(contactUs.getSubject());
            message.setText(contactUs.getMessage());
            message.setFrom(adminMail);
            message.setReplyTo(contactUs.getEmail());

            javaMailSender.send(message);
            return true;
        }
        catch(Exception ex){
            throw new RuntimeException("Unexpected error occurred, failed to send email to"+
                                       " the admin.");
        }
    }

    private void sendAutoReplyToClient(ContactUs contactUs){

        try{
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(contactUs.getEmail());
            msg.setSubject("Thank you for contacting us!");
            msg.setText("Dear " + contactUs.getName() + ",\n\n" + "We have received your message. " +
                    "Our team will soon contact you. \n\n" + "Regards,\nGreen Corner Team");
            javaMailSender.send(msg);
        }
        catch (Exception ex){
            throw new RuntimeException("Unexpected error occurred while sending message/complaint " +
                    " auto-reply email to " + contactUs.getName());
        }

    }
}
