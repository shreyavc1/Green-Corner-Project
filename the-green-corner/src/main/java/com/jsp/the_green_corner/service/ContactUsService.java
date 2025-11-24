package com.jsp.the_green_corner.service;

import com.jsp.the_green_corner.entity.ContactUs;

public interface ContactUsService {

    String saveAndSendEmailToAdminAndAutoReplyPlusManualReply(ContactUs contactUs);
}
