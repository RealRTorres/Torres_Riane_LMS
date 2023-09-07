/*
* Riane Torres
* Co. No. 14835
* 9/7/23
* CEN 3024C
*  */
public class LMSRunner {
    public static void main(String[] args) {
        LMS artOfComputerProgramming = new LMS(1000);
        LMS effectiveJava = new LMS(1000);
        LMS clearCode = new LMS(1000);

        artOfComputerProgramming.read();
        effectiveJava.read();
        clearCode.read();

        artOfComputerProgramming.setCopies(4);
        effectiveJava.setCopies(6);
        clearCode.setCopies(10);

        artOfComputerProgramming.checkedIn(5);
        effectiveJava.checkedIn(7);
        clearCode.checkedIn(10);

        artOfComputerProgramming.checkedOut(6);
        effectiveJava.checkedOut(10);
        clearCode.checkedOut(20);

        System.out.println(artOfComputerProgramming.getCopies());
        System.out.println(effectiveJava.getCopies());
        System.out.println(clearCode.getCopies());
    }

}
