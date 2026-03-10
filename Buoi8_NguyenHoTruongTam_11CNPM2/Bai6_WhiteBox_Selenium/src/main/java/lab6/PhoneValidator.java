package lab6;

public class PhoneValidator {

public static boolean isValid(String phone){

if(phone == null || phone.trim().isEmpty())
return false;

phone = phone.replace(" ","");

if(phone.startsWith("+84"))
phone = "0" + phone.substring(3);

if(!phone.matches("[0-9]+"))
return false;

if(phone.length()!=10)
return false;

return phone.matches("^(03|05|07|08|09)[0-9]{8}$");
}
}