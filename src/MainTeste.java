import model.HttpPage;


public class MainTeste {
	public static void main(String args[]){
		
		HttpPage site = new HttpPage("https://www.google.com.br");
		
		site.getOnlyHTML();
		
		
	}

}
