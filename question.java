public class question{
	 private String question;
	 private int num_c;
	 private String[] choices;
	 private int ans, apt, cor_apt,guess;
	 public void init_que(String str){
	 	question=str;
	 }

	 public String get_que(){
	 	return question;
	 }

	 public void init_numc(int num){
	 	num_c=num;
	 }

	 public int get_num(){
	 	return num_c;
	 }

	 public void choices(String[] str){
	 	choices=str;
	 }

	 public String get_choice(int i){
	 	return choices[i];
	 }

	 public void init_ans(int answ){
	 	ans=answ;
	 }

	 public int get_ans(){
	 	return ans;
	 }
	 
	 public void init_apt(int num){
		apt=num;
	 }

	 public int get_apt(){
	 	return apt;
	 }
	 
	 public void apt(){
	 	apt++;
	 }

	 public void init_cor(int num){
		cor_apt=num;
	 }

	 public void cor(){
	 	cor_apt++;
	 }

	 public int get_cor(){
	 	return cor_apt;
	 }

	 public void init_guess(int num){
		guess=num;
	 }

	 public int get_guess(){
	 	return guess;
	 }

}