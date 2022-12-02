public class Main {
    public static void main(String[] args) {
        System.out.println(isValidMail("12345@qq.com"));
        System.out.println(isValidMail("@qq.com"));
        System.out.println(isValidMail("12345@.com"));
        System.out.println(isValidMail("12345@qq."));
        System.out.println(isValidMail("123$45@qq.com"));
    }
    public static boolean isValidMail(String str) {
        String regex="\\w+@\\w+.\\w+";
        return str.matches(regex);
    }
}
