package com.highluck.gamseong.common.library;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;

import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.velocity.app.VelocityEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public final static String MAIL_SUCCESS = "인증 메일 성공";
	public final static String MAIL_FAIL = "인증 메일 실패";
	public final static String PASSWORD_MSG = "패스워드를 입력해주세요.(8자 ~ 16자 사이 영숫자 혼합)";

	
	public void sendMail(MailMessage msg){
		
		SimpleMailMessage  mail = new SimpleMailMessage();		
		
		mail.setFrom("peoplejobis@gmail.com");
		mail.setTo(msg.getReciver());
		mail.setSubject(msg.getSubject());
		mail.setText(msg.getMessage());
			
		javaMailSender.send(mail);
	}
	
	public void sendAuthMail(final MailMessage msg) throws MessagingException {

		
		final Context ctx = new Context();
		ctx.setVariable("bg_img", "img/mail/auth_background.png");
		ctx.setVariable("btn", " img/mail/auth_btn.png");
		ctx.setVariable("logo", " img/mail/logo_btn.png");
		ctx.setVariable("face", "img/mail/facebook_btn.png");
		ctx.setVariable("insta", " img/mail/insta_btn.png");
		ctx.setVariable("cafe", " img/mail/cafe_btn.png");
		ctx.setVariable("mail", msg.getReciver());
		ctx.setVariable("token", msg.getTokenKey());
			  
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		final MimeMessageHelper message =
		new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
		message.setSubject(msg.getSubject());
		message.setFrom("thymeleaf@example.com");
		message.setTo(msg.getReciver());

		final String htmlContent = this.templateEngine.process("authEmail.html", ctx);
		message.setText(htmlContent, true); 
		
		byte[] img = getImgByte("/img/mail/auth_background.png");
		byte[] btn = getImgByte("/img/mail/auth_btn.png");
		byte[] logo = getImgByte("/img/mail/logo_btn.png");
		byte[] face = getImgByte("/img/mail/facebook_btn.png");
		byte[] insta = getImgByte("/img/mail/insta_btn.png");
		byte[] cafe = getImgByte("/img/mail/cafe_btn.png");
		
		if(img != null){
			message.addInline("auth_background.png", new ByteArrayResource(img), "image/png");
			message.addInline("auth_btn.png", new ByteArrayResource(btn), "image/png");
			message.addInline("logo_btn.png", new ByteArrayResource(logo), "image/png");
			message.addInline("facebook_btn.png", new ByteArrayResource(face), "image/png");
			message.addInline("insta_btn.png", new ByteArrayResource(insta), "image/png");
			message.addInline("cafe_btn.png", new ByteArrayResource(cafe), "image/png");
		}

		javaMailSender.send(mimeMessage);
	}
	
	public void sendPasswordMail(final MailMessage msg) throws MessagingException {

		final Context ctx = new Context();
		
		ctx.setVariable("bg_img", "img/mail/pass_background.png");
		ctx.setVariable("btn", " img/mail/password_btn.png");
		ctx.setVariable("logo", " img/mail/logo_btn.png");
		ctx.setVariable("face", "img/mail/facebook_btn.png");
		ctx.setVariable("insta", " img/mail/insta_btn.png");
		ctx.setVariable("cafe", " img/mail/cafe_btn.png");
		ctx.setVariable("mail", msg.getReciver());
		ctx.setVariable("token", msg.getTokenKey());
			  
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		final MimeMessageHelper message =
		new MimeMessageHelper(mimeMessage, true, "UTF-8");
		message.setSubject(msg.getSubject());
		message.setFrom("thymeleaf@example.com");
		message.setTo(msg.getReciver());
	
		final String htmlContent = this.templateEngine.process("passwordEmail.html", ctx);
		message.setText(htmlContent, true); 
		
		byte[] img = getImgByte("/img/mail/pass_background.png");
		byte[] btn = getImgByte("/img/mail/password_btn.png");
		byte[] logo = getImgByte("/img/mail/logo_btn.png");
		byte[] face = getImgByte("/img/mail/facebook_btn.png");
		byte[] insta = getImgByte("/img/mail/insta_btn.png");
		byte[] cafe = getImgByte("/img/mail/cafe_btn.png");
		
		if(img != null){
			message.addInline("pass_background.png", new ByteArrayResource(img), "image/png");
			message.addInline("password_btn.png", new ByteArrayResource(btn), "image/png");
			message.addInline("logo_btn.png", new ByteArrayResource(logo), "image/png");
			message.addInline("facebook_btn.png", new ByteArrayResource(face), "image/png");
			message.addInline("insta_btn.png", new ByteArrayResource(insta), "image/png");
			message.addInline("cafe_btn.png", new ByteArrayResource(cafe), "image/png");
	}
	javaMailSender.send(mimeMessage);
}
	
	
	public byte[] getImgByte(String fileName){
		byte[] image;
		try {
			InputStream is = this.getClass().getResourceAsStream(fileName);
			image = org.apache.commons.io.IOUtils.toByteArray(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return image;
	}
	
}
