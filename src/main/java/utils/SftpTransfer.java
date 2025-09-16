package utils;

import java.io.File;
import java.util.List;
import com.vimalselvam.cucumber.listener.Reporter;
import constants.SFTPConstants;
import enums.SFTP;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpTransfer {


	public static void downloadORUpload(List<String> listOfFiles, String remoteFileLoc, SFTP actionType) {
		JSch jsch = new JSch();
		Session session = null;
		ChannelSftp sftpChannel = null;
		Channel channel = null;
		try {
			session = jsch.getSession(SFTPConstants.getSftpUserName(), SFTPConstants.getSftpHostName(),
					Integer.parseInt(SFTPConstants.getSftpPortId()));
			String pwd = "a0T*|rK\"2oB~pSwW";
			session.setPassword(pwd);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			switch (actionType) {
			case UPLOAD:
				for (String file : listOfFiles) {
					sftpChannel.put(file, remoteFileLoc);
				}
				break;
			case DOWNLOAD:
				for (String file : listOfFiles) {
					sftpChannel.get(file, remoteFileLoc);
				}
				break;
			default:
				break;
			}
		} catch (SftpException | JSchException e) {
			e.printStackTrace();
			System.out.println("Exception occurred while uploading the files!!!!!!!!! Please check");
		} finally {
			sftpChannel.exit();
			session.disconnect();
			channel.disconnect();
		}
	}

	public static boolean downloadAndSave(String fileName, String remoteFileLoc) {
		boolean isMatched = false;
		JSch jsch = new JSch();
		Session session = null;
		ChannelSftp sftpChannel = null;
		Channel channel = null;
		File dir = new File(remoteFileLoc);
		File[] dirContent = dir.listFiles();
		try {
			session = jsch.getSession(SFTPConstants.getSftpUserName(), SFTPConstants.getSftpHostName(),
					Integer.parseInt(SFTPConstants.getSftpPortId()));
			jsch.addIdentity(SFTPConstants.getSftpPemFileLoc());
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;

			sftpChannel.get(fileName, remoteFileLoc);
			for (int i = 0; i < dirContent.length; i++) {
				if (dirContent[i].getName().equals(fileName)) {
					isMatched = true;
					Reporter.addStepLog("Expected text :: " + "<b style=\"color:green;\">"
							+ " </b> :: File Successfully Downloaded :: " + "<b style=\"color:green;\"></b>");
				} else {
					Reporter.addStepLog("Expected text ::  " + "<b style=\"color:red;\"> </b>"
							+ " :: Exception occurred while Downloading the files  :: "
							+ "<b style=\"color:red;\"> </b>");
				}

			}

		} catch (SftpException | JSchException e) {
			e.printStackTrace();
			System.out.println("Exception occurred while Downloading the files!!!!!!!!! Please check");

		} finally {
			sftpChannel.exit();
			session.disconnect();
			channel.disconnect();
		}
		return isMatched;
	}
}