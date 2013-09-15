package milliwatt.utils;

public class Global {
	//sempre termina com BARRA
	public static final String KEY_DIR = "./KEY/";
	public static final String HTML_DIR = "./HTML/";
	public static final String MW_HOME = "https://matriculaweb.unb.br/matriculaweb/";
	
	
	public static final String MW_GRADUATE = MW_HOME + "graduacao/";
	public static final String MW_POSTGRADUATE = MW_HOME + "posgraduacao/";
	
	public static final String MW_CAMPUS = MW_GRADUATE + "oferta_campus.aspx";//obter lista de campus
	public static final String MW_CAMPUS_ID = "cod=";//utilizado para parse de campus
	public static final String MW_DEPARTAMENT_ID = "cod=";
	public static final String MW_DISCIPLINE_ID = "oferta_dados.aspx?cod=";
	public static final String MW_DISCIPLINE_PRE_REQ_ID = "-req:";
	public static final String MW_CLASS_ID ="<font-size=4><b>";
	public static final String MW_CLASS_VACANCIES ="Vagas:";
	public static final String MW_CLASS_VACANCIES_OCCUPIED ="Ocup.:";
	public static final String MW_CLASS_VACANCIES_AVAILABLE = "Disp.:";
	public static final String MW_CLASS_DAYS_BEGIN = "border: white 1px solid";
	public static final String MW_CLASS_DAYS_END = "Reserva/Vagas";
	
	public static final String DAR_CODE_ID = "1";
	public static final String PLA_CODE_ID = "2";
	public static final String CEI_CODE_ID = "3";
	public static final String FGA_CODE_ID = "4";
	//cod de 1 a 4 sendo 4 o Gama
	public static final String MW_DEPARTMENT = MW_GRADUATE+"oferta_dep.aspx?cod="; //obter lista de departamentos e seus codigos.
	
	public static final String MW_DISCIPLINE =  MW_GRADUATE + "oferta_dis.aspx?cod=";//obter lista de disciplinas e seus codigos.
	
	public static final String MW_DISCIPLINE_PRE_REQ = MW_GRADUATE + "disciplina_pop.aspx?cod=";
	
	// obter lista de turmas e informações de horarios e professores e etc
	public static final String MW_CLASS_DISCIPLINE = MW_GRADUATE + "oferta_dados.aspx?cod=";
	public static final String MW_CLASS_DEPARTMENT = "&dep="; 	
	
		
}
