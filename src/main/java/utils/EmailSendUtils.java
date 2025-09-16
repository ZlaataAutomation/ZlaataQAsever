

package utils;

import javax.mail.MessagingException;


import mails.EmailAttachmentsSender;
import mails.EmailConfig;
import manager.FileReaderManager;
import constants.ZlaataUIConstants;

public class EmailSendUtils {

    

    public static void sendEmail() {
        System.out.println("****************************************");
        System.out.println("Email send - START");
        System.out.println("****************************************");

        if (FileReaderManager.getInstance().getConfigReader().isSendReportThroughMail()) {
            System.out.println("File name: " + ZlaataUIConstants.REPORTS_CUCUMBER_LOCATION);
            String messageBody = getTestCasesCountInFormat();
            System.out.println(messageBody);

            try {
                EmailAttachmentsSender.sendEmailWithAttachments(EmailConfig.SERVER, EmailConfig.PORT, EmailConfig.FROM, EmailConfig.PASSWORD, EmailConfig.TO, EmailConfig.SUBJECT, messageBody,
                		ZlaataUIConstants.REPORTS_CUCUMBER_LOCATION);

                System.out.println("****************************************");
                System.out.println("Email sent successfully.");
                System.out.println("****************************************");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }

    }

    private static String getTestCasesCountInFormat() {

        return "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
                + "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
                + ZlaataUIConstants.PROJECT_NAME + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
                + "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
                + "</td></tr>\r\n" + "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
                + "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
                + "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
                + "</td></tr>\r\n" + "                     <tr><td align=\"center\">Passed</td></tr>\r\n"
                + "       \r\n" + "                </table>\r\n" + "                </td>\r\n"
                + "                <td>\r\n" + "                <table style=\"background:#ff5454;width:120px\">\r\n"
                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
                + "</td></tr>\r\n" + "                     <tr><td align=\"center\">Failed</td></tr>\r\n"
                + "       \r\n" + "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
                + "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
                + "</td></tr>\r\n" + "                     <tr><td align=\"center\">Skipped</td></tr>\r\n"
                + "       \r\n" + "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
                + "                </tr>\r\n" + "               \r\n" + "                \r\n"
                + "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";
    }

}
