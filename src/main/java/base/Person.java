package base;

class Person {
    public static void main(String[] args) {
        String str1 = "hello"; //str1指向静态区
        String str2 = new String("hello");  //str2指向堆上的对象
        String str3 = "hello";
        String str4 = new String("hello");
        System.out.println(str1.equals(str2)); //true
        System.out.println(str2.equals(str4)); //true
        System.out.println(str1 == str3); //true
        System.out.println(str1 == str2); //false
        System.out.println(str2 == str4); //false
        System.out.println(str2 == "hello"); //false
        str2 = str1;
        System.out.println(str2 == "hello"); //true
    }


}
