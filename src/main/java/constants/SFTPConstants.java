package constants;

import java.io.File;

public final class SFTPConstants {
	private static final String SFTP_HOST_NAME = "192.168.0.134";
    private static final String SFTP_USER_NAME = "ZlaataUser";
    private static final String SFTP_PORT_ID = "22";
    private static final String SFTP_PEM_FILE_LOC = System.getProperty("user.dir") + File.separator + "src/main/resources/Key/export.pem";

    public static String getSftpHostName(){
        return SFTP_HOST_NAME;
    }
    public static String getSftpUserName(){
        return SFTP_USER_NAME;
    }
    public static String getSftpPortId(){
        return SFTP_PORT_ID;
    }
    public static String getSftpPemFileLoc(){
        return SFTP_PEM_FILE_LOC;
    }

}
