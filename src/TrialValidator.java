public class TrialValidator {
    public static String StringValidate(String target, String message) throws CustomException {
        String regex = "[a-zA-ZА-Яа-яЁё]*";
        if (target == null || target.isEmpty() || !target.matches(regex)) { 
            throw new CustomException(message);
        }       
        return target;  
    }  

    public static int IntValidate(int target, int maxNum, int minNum, String message) throws CustomException {   
        if (!(target > minNum && target < maxNum)) { 
            throw new CustomException(message);
        } 
        return target;
    } 
}
