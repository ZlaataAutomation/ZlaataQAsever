/**
 * @author Raged Versa
 * https://www.linkedin.com/in/rajat-v-3b0685128/
 * https://github.com/rajatt95
 * https://rajatt95.github.io/
 *
 * Course: Cucumber BDD Masterclass with Selenium 4 & Java + Framework (https://www.udemy.com/course/cucumber-bdd-masterclass/)
 * Tutor: Omprakash Chavan (https://www.udemy.com/user/omprakash-chavan/)
 */

/***************************************************/

package mails;

import constants.ZlaataUIConstants;

/**
 * Data for Sending EMail after execution
 */
public class EmailConfig {

    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "587";

    public static final String FROM = "ranjithganesan.testingzlaata@gmail.com";
    public static final String PASSWORD = "zozvabpdotwqcjes";
    public static final String[] TO = {"ranjith.g@handletechlabs.com","divyashree@thewholewave.com","alexrozario.jd@elitoinnovations.com","gowthamraj.p@elitoinnovations.com","sarojkumar@handletechlabs.com"};
    public static final String SUBJECT = ZlaataUIConstants.SUBJECTS;
    


	}

//,"divyashree@thewholewave.com","gowthamraj.p@elitoinnovations.com","alexrozario.jd@elitoinnovations.com"