import model.HttpPage;
import model.HttpsPage;


public class MainTeste {
	public static void main(String args[]){
		
		HttpsPage site = new HttpsPage("https://matriculaweb.unb.br/matriculaweb/graduacao/oferta_dados.aspx?cod=203769&dep=650");
		site.connect();
		
		
		site.getOnlyHTML();
		
		site.disconnect();
	}

}
