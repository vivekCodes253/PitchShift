import java.util.Scanner;
import java.io.*;

class PitchShift
{
    public static String fp;

    /*Function Name : sop
    Purpose         : Simplifies System.out.print()
    Input           : String to output
    Return          : --    */
    public static void sop(String s){System.out.print(s);}


    /*Function Name : 
    Purpose         : 
    Input           : 
    Return          :     */
    public static void test(){}


    /*Function Name : menu
    Purpose         : Display menu, get user choice and perform operation
    Input           : 
    Return          : True if menu needs to be displayed again, false if exit condition    */
    public static boolean menu()
    {
        int choice;
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        if(choice==4)
            return false;
        return true;
    }

    /*Function Name : fileread
    Purpose         : read data from file
    Input           : filepath
    Return          : Read message or status    */
    public static String fileread(String filepath)
    {
        File file = new File(filepath);
        String str, read_string = "";
        try{
            BufferedReader in = new BufferedReader(new FileReader(file)); 
            while ((str = in.readLine()) != null) 
            {
                read_string += str;     
            }   
        }
        catch(FileNotFoundException ex)
        {
            return("\nFile not found, create one or check the path!\n");
        }
        catch(Exception e)
        {
            return(e+"\n");
        }

        return read_string;
    }

    /*Function Name : pitchvonv
    Purpose         : Converts string with one pitch data to another mentioned by parameter
    Input           : String of notes, amount to shift (+/-)
    Return          : New string with modified pitches    */
    public static void pitchconv
    {

    }

    

    //Argument will be the file path
    public static void main(String[] args)
    {
        fp = args[0];
        String data = fileread(fp);

        test();
        
    }
}