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
    public static void test()
    {
        Scanner sc = new Scanner(System.in);
        int a,b,c,d;
        while(menu())
        {
            sop("\nEnter 4 parameters:");
            
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            d = sc.nextInt();
            sop("\nSoln is "+circleator(a,b,c,d));
        }

    }

    /*Function Name : getUserData  [i/o operation]
    Purpose         : Get user data 
    Input           : --
    Return          : String tring accepted from the user  */
    public static int getUserDataInt(String message)
    {
        Scanner sc = new Scanner(System.in);
        sop("\n"+message);
        int return_value = sc.nextInt();
        return(return_value);
    }


    /*Function Name : menu
    Purpose         : Display menu, get user choice and perform operation
    Input           : 
    Return          : True if menu needs to be displayed again, false if exit condition    */
    public static boolean menu()
    {
        int choice;
        sop("\nEnter : ");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        if(choice==-1)
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
                read_string += str + "\n";     
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

    /*Function Name : circleator
    Purpose         : Tell the circular position when a number is added to a limited number set
    Input           : current position, number set lower limit, number set upper limit, change(+/-)   [default for ll = 0] [[ll and ul inclusive]]
    Return          : New number    */
    public static int circleator(int current, int ll, int ul, int change)
    {
        current = current - ll;
        current = current + change;
        current = current% (ul-ll+1);
        current = current + ll;
        if(current<0)
            current+=(ul-ll + 1);
        return current;
    }

    public static int circleator(int current, int ul, int change)
    {
        return circleator(current,0,ul,change);
    }

    /*Function Name : pitchvonv
    Purpose         : Converts string with one pitch data to another mentioned by parameter
    Input           : String of notes, amount to shift (+/-)
    Return          : New string with modified pitches    */

    public static boolean contains(String[] data, String targetValue) 
    {
        int i;
	    for(i=0;i<data.length;i++)
        {
            if(data[i].equals(targetValue))
                return true;
        }

        return false;
    }

    public static int getpos(String[] data, char value)
    {
        String tofind =""+value;
        int i;
        for(i=0;i<data.length;i++)
        {
            if((data[i]).equals(tofind))
                return i+1;
        }

        return -1;
    }
    public static String pitchconv(String data, int count)
    {

        String[] notes = {"C","Db","D","Eb","E","F","Gb","G","Ab","A","Bb","B"};
        String output = "";
        int pos,i;
        char val;
        for(i=0;i<data.length();i++)
        {
            val = data.charAt(i);
            if(contains(notes,val+""))
            {
                pos = getpos(notes,val);
                if(i!=data.length()-1 && data.charAt(i+1) == 'b')
                {
                    pos = pos -1;
                    i++;
                }
               // sop("\nCurrent pos = " + pos + " shifting to "+ circleator(pos,1,12,count));
                output+=(notes[circleator(pos,1,12,count)-1]);
                
            }
            else
                output+= val;
        }
        
        return output;  
    }

    public static void test2()
    {
        Scanner sc = new Scanner(System.in);
        while(menu())
        {
            sop("\nEnter data : ");
            String data = sc.nextLine();
            sop("\n"+pitchconv(data,12));

        }
    }

    /*Function Name : inputinterface
    Purpose         : Accepts user input of data or lets user select file
    Input           : 
    Return          :     */
    public static void inputinterface()
    {
        boolean status = true;
        while(status)
        {
            sop("\n1) Manually enter data\n2) Select file\n3) Exit");
            int choice;
            sop("\nEnter : ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            if(choice == 1)
            {
                sop("\nEnter data :");
                String data = sc.nextLine();
                data = sc.nextLine();
                execute(data,getUserDataInt("\nShift by : "));
            }

            if(choice == 2)
            {
                sop("\nEnter file path :");
                String fp = sc.nextLine();
                fp = sc.nextLine();
                execute(fileread(fp),getUserDataInt("\nShift by : "));
            }
            if(choice==3)
                status =  false;
        }      
        
    }

    /*Function Name : preprocess
    Purpose         : To convert all sharps to relative flats
    Input           : Data
    Return          : Preprocessed data    */
    public static String preprocess(String data)
    {
        int i;
        String return_data = "";
        int len = data.length();
        for(i=0;i<len;i++)
        {
            if(i!=len-1 && (data.charAt(i+1)=='#'))
            {
                String val = pitchconv(data.charAt(i)+"",1);
                return_data += (val);
                i++;
            }
            else
                return_data+= data.charAt(i);   
            
        }

        return return_data;
    }
    

    /*Function Name : execute
    Purpose         : To convert and display data
    Input           : Data, shift count
    Return          :     */
    public static void execute(String data, int shift)
    {
        data = preprocess(data);
        sop(pitchconv(data,shift)+ "\n");
    }

    

    //Argument will be the file path
    public static void main(String[] args)
    {

        if(args.length == 0)
            inputinterface();
        else
        {
            execute(fileread(args[0]),getUserDataInt("\nShift by : "));
        }

        

        //sop(preprocess("A# B D# F G"));
       // fp = args[0];
        //String data = fileread(fp);

        //test();
        //Need to pre process for # signs, maybe give user a choice for # and flat 
        //test2();

        
        
    }
}