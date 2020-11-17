package Validation;
public class Regexp {

   public final static String WORK_TIME = "\\(?P<day>Mon|Tue|Wed|Thu|Fri|Sat|Sun)(?:-(?P<today>Mon|Tue|Wed|Thu|Fri|Sat|Sun))?\\s+(?P<from>(?<=\\s)(?:(?:2[0-3])|(?:[01]?[0-9]))(?:\\:[0-5][0-9])?)-(?P<till>(?<=-)(?:(?:2[0-3])|(?:[01]?[0-9]))(?:\\:[0-5][0-9])?)";
   public final static String PHONE_NUMBER = "\\^(\\+\\d{1,3}\\-)?\\d{1,14}$";

}
