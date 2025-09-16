package dataProviders;



import com.github.javafaker.Faker;
import utils.Common;
import utils.DateUtils;

public class TestDataValueGenerator {

	private String randomNum;

	public TestDataValueGenerator() {
		randomNum = DateUtils.getCurrentLocalDateTimeStamp("yyMMddHHSSS");
	}

	protected String generateValueForExcelKey(String value) {
		Faker faker = new Faker();
		String valueForExcelKeyword = null;
		if (value.contains("Date")) {
			String dateReqFormat = value.split("\\|")[1].trim();
			valueForExcelKeyword = DateUtils.getCurrentLocalDateTimeStamp(dateReqFormat);
//            valueForExcelKeyword = DateUtils.addOrMinusDays(dateReqFormat, 1);
		} 
		else if (value.contains("Name"))
		{
			if (value.split("\\|").length == 2)
			{
				valueForExcelKeyword = faker.name().firstName();
				if (valueForExcelKeyword.length() == Integer.parseInt(value.split("\\|")[1].trim())) {
					
				}
				else if (valueForExcelKeyword.length() > Integer.parseInt(value.split("\\|")[1].trim())) {
					valueForExcelKeyword = valueForExcelKeyword.substring(0,
							Integer.parseInt(value.split("\\|")[1].trim()));
				} 
				else if (valueForExcelKeyword.length() < Integer.parseInt(value.split("\\|")[1].trim())) {
					valueForExcelKeyword = valueForExcelKeyword + Common.getSaltString(
							Integer.parseInt(value.split("\\|")[1].trim()) - valueForExcelKeyword.length());
				}
			} 
			else 
			{
				valueForExcelKeyword = faker.name().lastName();
			}
		} 
		else if (value.equalsIgnoreCase("runnum")) {
			valueForExcelKeyword = randomNum + faker.number().numberBetween(666, 999);
		}
		else if (value.equalsIgnoreCase("shortname")) {
			valueForExcelKeyword = faker.name().lastName();
		} 
		else 
		{
			throw new RuntimeException(value + " ::: "
					+ "The value not configured in the TestDataValueGenerator class !!!! Kindly check this one");
		}
		return valueForExcelKeyword;
	}

//	public boolean isReqRunTimeValueGen(String value) {
		String[] values = { "runNum", "Name", "Date", "ShortName" };
//		List<String> list = List.of(values);
//		return list.contains(value.split("\\|")[0].trim());
	}

