import java.util.*;
import java.io.*;

public class quiz{

	static ArrayList <question> questions = new ArrayList <question>();
	static Scanner sc= new Scanner(System.in);// global scanner in file
	public static Scanner input;
	
	// initialize
	public static void init(String arg){

		File file=new File(arg);
		try{
			if (!file.exists()){
				System.out.println("File does not exists, please double check!");
				System.exit(0);
			}
		input = new Scanner(file);
		int index=0;//which question
			while (input.hasNext()){
				//read each questions
				questions.add(read(index));
				index++;
			}
		}catch(Exception exc){
		//	System.out.println(exc);
		}
		
	}

	//read question
	public static question read(int index){
		
			String s;
			question ques=new question();

			//question name
			ques.init_que(input.nextLine());
			System.out.println("Question "+index+":");
			System.out.println(ques.get_que());
			s=input.nextLine();

			//num of choices
			ques.init_numc(Integer.parseInt(s));
			String[] str=new String[ques.get_num()];
			for (int i=0;i<ques.get_num();i++)
			{				
				str[i]=input.nextLine();//choices
				System.out.println(i+":"+str[i]);
			}
			ques.choices(str);

			//answer
			s=input.nextLine();
			ques.init_ans(Integer.parseInt(s));

			//attampts
			s=input.nextLine();
			ques.init_apt(Integer.parseInt(s));

			//correct attampts
			ques.init_cor(Integer.parseInt(input.nextLine()));

			//guess
			int ans=-1;
			while (!((ans>=0)&&(ans<ques.get_num()))){
				System.out.print("Your answer? (enter a number): ");
				s=sc.next();
				try{
					ans=Integer.parseInt(s);
					ques.init_guess(ans);
				}catch(Exception e){}	
				s=sc.nextLine();
				System.out.println();
			}

			//compare
			ques.apt();
			if (ans==ques.get_ans()){
				ques.cor();
			}
		return ques;
		//}
	}

	//cumulative
	public static void cumulative(){
		//cumulative 
		System.out.println("Here are some cumulative statistics: ");
		double[] ptg=new double[questions.size()];
		for (int i=0; i<questions.size();i++){
			question que=questions.get(i);
			ptg[i]=que.get_cor()*100/(double)que.get_apt();//create a percentage array for compare easitest and hardist questions
			//System.out.println(ptg[i]);
			System.out.println("Question "+i+": "+que.get_que());
			System.out.println("\t\tTimes Tried: "+que.get_apt());
			System.out.println("\t\tTimes Correct: "+que.get_cor());
			System.out.printf ("		Percent Correct: %.1f",ptg[i]);
			System.out.println("%");
		}
		System.out.println();

		//find eaist and hardist 
		int min=0,max=0;
		for (int i=0;i<ptg.length;i++){
			if (ptg[max]<ptg[i]){max=i;}
			if (ptg[min]>ptg[i]){min=i;}
		}
		
		System.out.println("Easiest Question: ");
		System.out.println("Question: "+questions.get(max).get_que());
		System.out.println("\t\tTimes Tried: "+questions.get(max).get_apt());
		System.out.println("\t\tTimes Correct: "+questions.get(max).get_cor());
		System.out.printf ("\t\tPercent Correct: %.1f",ptg[max]);
		System.out.println("%\n");

		System.out.println("Hardest Question: ");
		System.out.println("Question: "+questions.get(min).get_que());
		System.out.println("\t\tTimes Tried: "+questions.get(min).get_apt());
		System.out.println("\t\tTimes Correct: "+questions.get(min).get_cor());
		System.out.printf ("		Percent Correct: %.1f",ptg[min]);
		System.out.println("%\n");

	}

	//process
	public static void process(){
		int cor=0,wrong=0;
		for (int i=0; i<questions.size();i++){

			question que=questions.get(i);
			System.out.println("Question "+i+":");
			System.out.println(que.get_que());
			System.out.println("Correct answer: "+que.get_choice(que.get_ans()));
			System.out.println("Your answer: "+que.get_choice(que.get_guess())+"");
			if (que.get_ans()==que.get_guess()){
				System.out.println("Result: CORRECT! Great Work!\n");
				cor++;
			}else{
				System.out.println("Result: INCORRECT! Remember the answer for next time!\n");
				wrong++;
			}
		}

		//overall grade
		System.out.println("Your overall performance was:");
		System.out.println("Right: "+cor);
		System.out.println("Wrong: "+wrong);
		double pct=cor*100/(double) questions.size();
		System.out.printf("Pct: %.1f",pct);
		System.out.println("%\n");

		cumulative();		
		
	}
	//file output
	public static void writefile(String arg){
		try{
			FileWriter fwriter = new FileWriter(arg);
			PrintWriter outputFile = new PrintWriter(fwriter);
			for (int i=0; i<questions.size();i++){
				question que=questions.get(i);
				outputFile.println(que.get_que());//print question
				outputFile.println(que.get_num());//print number of choices
				for (int j=0; j<que.get_num();j++){
					outputFile.println(que.get_choice(j));//print the list of choices
				}
				outputFile.println(que.get_ans());//print index of answer
				outputFile.println(que.get_apt());//print attampts
				outputFile.println(que.get_cor());//print number of correct attampts	
			}
			outputFile.close();
		}catch(Exception exc){System.out.println(exc);}
	}


	public static void main(String[] args) {
		//read file name from terminal
		String arg = "";
		do {
			if (args.length > 0) {
				arg = args[0];
			} else {
				System.out.println("Please type the quiz file");
				String s=sc.nextLine();
				arg=s;
				//System.exit(0);
			}
		} while(arg.length()==0);
		init(arg);
		process();
		writefile(arg);

	}
}