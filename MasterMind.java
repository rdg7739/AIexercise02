package mastermind;


import java.util.*;


public class MasterMind {
	private static int fixedNum = 0;
	private static int REACHGOAL = -2; 
	private int playCount = 0;
	private Scanner scanner = new Scanner(System.in);
	private int numColor;
	private int numPeg;
	private final int NO_VALUE = -1;

	private ArrayList<Integer> goal;
	private ArrayList<Integer> copy_goal;
	private ArrayList<Integer> guess = new ArrayList<Integer>(Arrays.asList(-1, -1, -1, -1));
	private Random generator = new Random();
	private ArrayList<Integer> finalGuess = new ArrayList<Integer>();
	private ArrayList<Integer> colors = new ArrayList<Integer>();

	public MasterMind(){

		System.out.print("Please Enter the Number of Possible Colors,\n Followed by a Space, Followed by the Number of Pegs: ");

		//numColor = scanner.nextInt();
		numColor = 100;
		numPeg = 10;
		//numPeg = scanner.nextInt();

		goal = new ArrayList<Integer>(numPeg);

		for(int i = 0; i < numColor; i++){

			colors.add(i);

		}
		for(int i = 0; i < numPeg; i++){

			finalGuess.add(NO_VALUE);

		}
		generateGoal();
		showStats();

		while(!isGoal()){
			takeGuess();
		}
		showStats();

	}


	public void isRight(ArrayList<Integer> array){

		String num = "0";

		for(int i = 0; i < numPeg; i++){

			if(i == 0)
				num = "";
			num += array.get(i);

		}

		for(int i = 0; i < numPeg; i++){
			System.out.print(" "+array.get(i));
		}

		System.out.print("\n");

	}

	public void takeGuess(){
		int j = 0, redNum;
		for(int i =0; i <= numColor; i ++){
			int colorTemp = colors.get(j);
			guess = (ArrayList<Integer>) finalGuess.clone();
			for(int k =0; k < numPeg; k++){
				if(finalGuess.get(k).equals(-1)){
					guess.set(k, colorTemp);
				}
			}
			redNum = checkRed(guess);
			if(redNum == REACHGOAL){
				break;
			}
			if(redNum- fixedNum!= 0){
				fixedNum = fixPosition(colorTemp);
			}
			j++;
		}


	}

	public int fixPosition(int color){
		for(int i =0; i< numPeg; i++){
			ArrayList<Integer> guess = (ArrayList<Integer>) finalGuess.clone();
			if(guess.get(i).equals(-1)){
				guess.set(i, color);
			}
			if(checkRed(guess)-fixedNum != 0){

				finalGuess.set(i, color);
				fixedNum++;
			}
		}
		return fixedNum;
	}

	public int checkRed(ArrayList<Integer> list){
		playCount++;
		int redNum =0, whiteNum = 0;
		for(int i =0; i < numPeg; i++){
			if(list.get(i).equals(goal.get(i))){
				redNum++;
			}
		}
		if(redNum == numPeg){
			finalGuess = (ArrayList<Integer>) list.clone();
			return REACHGOAL;
		}
		return redNum;
	}


	public void showStats(){

		System.out.println("\nYou Win!");
		String s = "";
		for(int i = 0; i < numPeg; i++){
			s += goal.get(i) + " ";
		}
		System.out.println(String.format("%-33s %4s", "Goal Peg List: ", s));
		System.out.println(String.format("%-36s %4d", "Number of Guesses Made: ", playCount));
	}

	public void generateGoal(){
		for(int i = 0; i < numPeg; i++){
			goal.add(generator.nextInt(numColor));
		}
		System.out.println("\nGoal Pegs has been Created. Start the Game!\n");
	}

	public boolean isGoal(){
		boolean isGoal = true;
		int i = 0;
		while(isGoal && i < numPeg){
			if(!goal.get(i).equals(guess.get(i)))
				isGoal = false;
			i++;
		}
		return isGoal;
	}

	public static void main(String args[]){

		MasterMind main = new MasterMind();

	}	

}