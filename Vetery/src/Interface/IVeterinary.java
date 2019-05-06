package Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import model.Client;
import model.Drug;
import model.MedRec;
import model.Pet;
import model.TypeService;
import model.Veterinary;

public class IVeterinary {
	public static void main(String[] args) throws Exception {
		IVeterinary initial = new IVeterinary();
		initial.ejecut();

	}

	private Veterinary vetery;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private void ejecut() throws Exception {
		vetery = new Veterinary();
		boolean opened = true;
		while (opened) {
			opened = openMenu();
		}
		br.close();
	}

	private boolean openMenu() throws Exception {
		System.out.println("Bienbenido al servicio de veterinaria: " + vetery.toString());
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("Ingresa un numero para acceder:");
		System.out.println("1. Registrar Cliente");
		System.out.println("2. Registrar Mascota");
		System.out.println("3. Hospitalizar");
		System.out.println("4. Ver Historias Clinicas");
		System.out.println("5. Ver ingresos");
		System.out.println("6. Cambiar dirreccion y telefono de un cliente");
		System.out.println("7. Ver y/o agregar drogas a al vaterinaria");
		System.out.println("8. Agregar droga a una hospitalizacion");
		System.out.println("9. Agregar nota a una hospitalizacion");
		System.out.println("10. Agregar sintoma a una hospitalizacion");
		System.out.println("11. Calcular Ingresos por servicio");
		System.out.println("12. Calcular los ingresos totales de la veterinaria");
		System.out.println("13. Agregar nuevo servicio");
		System.out.println("14. Ver promedio de ingresos por servicios");
		System.out.println("15. Ver promedio de ingresos de la veterinaria en una semana");
		System.out.println("16. Calcular indice de masa corporar a una mascota");
		System.out.println("17. Cerrar Progrma");
		System.out.println("Otro numero. Mostrar el menu de nuevo");
		int select = Integer.parseInt(br.readLine());
		return startMethod(select);
	}

	private boolean startMethod(int select) throws Exception {
		if (select == 1) {
			if (RegisterClient()) {
				System.out.println("Operacion exitosa");
			}
		} else if (select == 2) {
			if (RegisterPet()) {
				System.out.println("Operacion exitosa");
			}
		} else if (select == 3) {
			if (Hospitalize()) {
				System.out.println("Se ha hospitalizado la mascota");
			}
		} else if (select == 4) {			
			if (seeHistory()) {
				System.out.println("Se ha hospitalizado la mascota");
			}
		} else if (select == 5) {
			seeIncome();
		} else if (select == 6) {
			if(changeAddressAndPhone()) {
				System.out.println("Operacion exitosa");
			}
		} else if (select == 7) {
			if(drugAddOrSee()) {
				System.out.println("Operacion exitosa");
			}
		} else if (select == 8) {
			if(addDrugHospi()) {
				System.out.println("Operacion existosa");
			}
		} else if (select == 9) {
			if(noteAdd()) {
				System.out.println("Operacion exitosa");
			}
		} else if (select == 10) {
			if(symtoAdd()) {
				System.out.println("Operacion exitosa");
			}
		} else if (select == 11) {
			if(calculateByService()) {
				System.out.println("Regresando al menu");
			}
		} else if (select == 12) {
			calculateTotal();
		} else if (select == 13) {
			if(addTypeService()) {
				System.out.println("Operacion exitosa");
			}
		} else if (select == 14) {
			if(averangeByType()) {
				System.out.println("Regresando al menu");
			}
		} else if (select == 15) {
			System.out.println(vetery.averageEarningsTotalWeek()+"Por semana");;
		}   else if (select == 16) {
			if(calculateIMC()) {
				System.out.println("Operacion exitosa");
			}
		}else if (select == 17) {
			return false;
		}
		return true;
	}

	private boolean averangeByType() throws Exception {
		
		System.out.println("Calcular promedio por servicios");
		for (int i = 0; i < vetery.getTypeServices().size(); i++) {
			System.out.println((i+1)+" "+vetery.getTypeServices().get(i).getTypeService()+" Costo:"+vetery.getTypeServices().get(i).getCost());
		}
		System.out.println("Seleccionar tipo de servicio");
		int selecMed=Integer.parseInt(br.readLine())-1;
		if(selecMed>vetery.getTypeServices().size()-1) {
			System.out.println("Seleccione un tipo de servicio de la lista");
			return false;
		}
		System.out.println(vetery.averageByServiceType(vetery.getTypeServices().get(selecMed)));
		return true;
	}

	private boolean calculateIMC() throws Exception {
		System.out.println("Calcular indice de masa corporal");
		seeMedRec();
		System.out.println("Selecciona una Hospitalizacion");
		int selecMed=Integer.parseInt(br.readLine())-1;
		if(selecMed>vetery.getMedRecs().size()-1) {
			System.out.println("Seleccione una hospitalizacion de la lista");
			return false;
		}
		System.out.println("El indice de masa corporal es de:"+vetery.getMedRecs().get(selecMed).getPet().CalculateIMC()+"kg/m^2");
		return true;
	}

	private boolean addTypeService() throws Exception {
		System.out.println("Agregar Tipo de servicio:\nEscribe el tipo de Servicio");
		String type=br.readLine();
		System.out.println("Escribe el costo del servicio");
		double cost=Double.parseDouble(br.readLine());
		if(type==null||type.equals("")) {
			System.out.println("No deje los campos vacios");
			return false;
		}
		vetery.addServiceType(type, cost);
		return true;
	}

	private void calculateTotal() {
		
		System.out.println("Ganancias Total con Servicios: "+ vetery.calculateEarningsTotal());
	}

	private boolean calculateByService() throws NumberFormatException, IOException {
		System.out.println("Calcular ingresos por servicios");
		for (int i = 0; i < vetery.getTypeServices().size(); i++) {
			System.out.println((i+1)+" "+vetery.getTypeServices().get(i).getTypeService()+" Costo:"+vetery.getTypeServices().get(i).getCost());
		}
		System.out.println("Seleccionar tipo de servicio");
		int selecMed=Integer.parseInt(br.readLine())-1;
		if(selecMed>vetery.getTypeServices().size()-1) {
			System.out.println("Seleccione un tipo de servicio de la lista");
			return false;
		}
		System.out.println(vetery.calculateEarningsByServiceType(vetery.getTypeServices().get(selecMed)));
		return true;
	}

	private boolean symtoAdd() throws Exception {
		System.out.println("Agregar un sintoma a una hospitalizacion");
		seeMedRec();
		System.out.println("Seleccione una hospitalizacio");
		int selecMed=Integer.parseInt(br.readLine())-1;
		if(selecMed>vetery.getMedRecs().size()-1) {
			System.out.println("Seleccione una hospitalizacion de la lista");
			return false;
		}
		
		System.out.println("Escriba un sintoma para agregarlo");
		String note=br.readLine();
		vetery.getMedRecs().get(selecMed).addSymtom(note);
		return true;
	}

	private boolean noteAdd() throws NumberFormatException, IOException {
		System.out.println("Agregar nota a una hospitalizacion");
		seeMedRec();
		System.out.println("Seleccione una hospitalizacio");
		int selecMed=Integer.parseInt(br.readLine())-1;
		if(selecMed>vetery.getMedRecs().size()-1) {
			System.out.println("Seleccione una hospitalizacion de la lista");
			return false;
		}
		
		System.out.println("Escriba una nota para la hospitalizacion");
		String note=br.readLine();
		vetery.getMedRecs().get(selecMed).addNoteDiagnostic(note);
		return true;
	}

	private boolean addDrugHospi() throws Exception {
		System.out.println("Agregar droga a una Hospitalizacion");
		seeDrugs();
		System.out.println("Selecciones una droga");
		int selec=Integer.parseInt(br.readLine())-1;
		if(selec>vetery.getListDrug().size()-1) {
			System.out.println("Seleccione una droga de la lista");
			return false;
		}
		seeMedRec();
		System.out.println("Seleccione una hospitalizacio");
		int selecMed=Integer.parseInt(br.readLine())-1;
		if(selecMed>vetery.getMedRecs().size()-1) {
			System.out.println("Seleccione una hospitalizacion de la lista");
			return false;
		}
		vetery.getMedRecs().get(selecMed).addDrug(vetery.getListDrug().get(selec));
		return true;
	}

	private void seeMedRec() {
		for (int i = 0; i < vetery.getMedRecs().size(); i++) {
			MedRec aux = vetery.getMedRecs().get(i);
			int index = i + 1;
			System.out.println(index + ". " + aux.toString());
		}
		
	}

	private boolean drugAddOrSee() throws Exception {
		System.out.println("Drogas en la veterinaria");
		seeDrugs();
		System.out.println("Quieres agregar una droga a la veterinaria\nY/N");
		String read=br.readLine();
		if(read.equals("Y")) {
			System.out.println("Introduce el nombre de la Droga");
			String name=br.readLine();
			System.out.println("Introduce la docis de la droga");
			String dose=br.readLine();
			System.out.println("Introduce el costo por docis(numero)");
			double costPerDose=Double.parseDouble(br.readLine());
			System.out.println("Introduce la fecuencia de docis(numero en dias)");
			int frecuanceAdmin=Integer.parseInt(br.readLine());
			if(name==null||dose==null||name.equals("")||dose.equals("")) {
				System.out.println("Los campos no pueden ser vacios");
				return false;
			}
			Drug newDrug=new Drug(name, dose, costPerDose, frecuanceAdmin);
			vetery.getListDrug().add(newDrug);
			return true;
		}
		return false;
		
		
	}

	private boolean changeAddressAndPhone() throws Exception {
		if(vetery.getClients().size()!=0) {
		System.out.println("Cambiar Direccion y Telefono");
		seeClients();
		System.out.println("Selecciones un usuario");
		int selec=Integer.parseInt(br.readLine())-1;
		if(selec>vetery.getClients().size()-1) {
			System.out.println("Seleccione un cliente de la lista");
			return false;
		}
		System.out.println("Escriba la nueva direccion");
		String address=br.readLine();
		System.out.println("Escriba el nuevo telefono");
		String tel=br.readLine();
		if(address==null||tel==null||address.equals("")||tel.equals("")) {
			System.out.println("No deje los campos vacios");
			return false;
		}
		Client aux=vetery.getClients().get(selec);
		aux.setAddress(address);
		aux.setTel(tel);
		return true;
		}
		System.out.println("No hay Clientes");
		return false;
		
	}

	private void seeIncome() {
		System.out.println("Ganancias: "+vetery.getEarnings());
		System.out.println("Ganacias serivicios: "+(vetery.calculateEarningsTotal()-vetery.getEarnings()));
		System.out.println("Ganancias Total con Servicios: "+ vetery.calculateEarningsTotal());
		
	}

	private boolean seeHistory() throws Exception {
		for (int i = 0; i < vetery.getMedRecs().size(); i++) {			
			int index=i+1;
			System.out.println(index+". "+vetery.infoPet(i));
			System.out.println("	Costo total= "+vetery.getMedRecs().get(i).CostTotal());
			
		}
		System.out.println("Para dar de alta coloca el numero del historial correspondiente:");
		int select=Integer.parseInt(br.readLine())-1;
		if(select>vetery.getMedRecs().size()-1) {
			System.out.println("Operacion cancelada. Selecciones un numero de la lista");
			return false;
		}
		return true;
	}

	private boolean Hospitalize() throws Exception {
		if (vetery.availabilityRoom()&&vetery.getListDrug().size()!=0) {

			System.out.println("HOSPIATALIZAR\n-------------------------------------");
			System.out.println("Clientes Registrados: SELECIONA UN CLIENTE ");
			seeClients();
			int select = Integer.parseInt(br.readLine()) - 1;
			Client auxClient = vetery.getClientPos(select);
			if(auxClient==null) {
				System.out.println("Seleccione un cliente de la lista");
				return false;
			}
			System.out.println("Mascotas Registrados: SELECIONA UN CLIENTE ");
			select = Integer.parseInt(br.readLine()) - 1;
			Pet auxPet = auxClient.getPetPos(select);
			if(auxPet==null) {
				System.out.println("Seleccione una mascota de la lista");
				return false;
			}
			System.out.println("Ingrese los sintomas separados por ','(coma)");
			
			ArrayList<String> symptom=(ArrayList<String>) Arrays.asList(br.readLine().split(","));
			System.out.println("Ingrese el siagnostico");
			String diagnosis=br.readLine();
			System.out.println("Selecciona las drogas de la lista (coloca los numeros separados po ',')");
			seeDrugs();
			String[] selecList=br.readLine().split(",");
			ArrayList<Drug> listDurg=new ArrayList<Drug>();
			for (int i = 0; i < selecList.length; i++) {
				if(Integer.parseInt(selecList[i])-1>vetery.getListDrug().size()-1) {
					System.out.println("Seleccione drogas de la lista");
					return false;
				}
				listDurg.add( vetery.getListDrug().get(Integer.parseInt(selecList[i])-1));				
			}
			
			System.out.println(vetery.findPastHospitali(auxClient,select));
			return vetery.addPetRoom(auxClient.getId(), select, symptom, diagnosis, listDurg, new Date());

		} else {
			return false;

		}
	}

	private void seeDrugs() {
		
		for (int i = 0; i < vetery.getListDrug().size(); i++) {
			Drug aux = vetery.getListDrug().get(i);
			int index = i + 1;
			System.out.println(index + ". " + aux.getName()+", Costo por docis: "+aux.getCostPerDose());
		}
		
	}

	private boolean RegisterPet() throws Exception {
		if(vetery.getClients().size()!=0) {
		System.out.println("REGISTAR MASCOTA\n----------------------------------");
		System.out.println("Clientes Registrados: SELECIONA UN CLIENTE ");
		seeClients();
		int select = Integer.parseInt(br.readLine()) - 1;
		Client auxClient = vetery.getClientPos(select);
		if(auxClient==null) {
			System.out.println("Seleccione un cliente de la lista");
			return false;
		}
		
		System.out.println("\nCliente seleccionado:");
		System.out.println(auxClient.getName() + " ---> ID:" + auxClient.getId() + "\n");
		System.out.println("Mascotas Registradas:");
		seePets(auxClient);

		String namePet = "";
		String type = "";
		int age = 0;
		double weight = 0;
		double height = 0;

		System.out.println("Ingrese el nombre para la mascota (Es unico)");
		boolean out = false;
		do {
			namePet = br.readLine();
			Pet aux = auxClient.findPetByName(namePet);
			if (aux != null) {
				System.out.println("Este nombre ya existe. Intente otro");
				out = true;
			} else {
				out = false;
			}
		} while (out);
		System.out.println("Seleccione el tipo del animal");
		System.out.println("1. " + Pet.BIRD);
		System.out.println("2. " + Pet.CAT);
		System.out.println("3. " + Pet.DOG);
		System.out.println("4. " + Pet.OTHER);
		select = Integer.parseInt(br.readLine());
		switch (select) {
		case 1:
			type = Pet.BIRD;
			break;
		case 2:
			type = Pet.CAT;
			break;
		case 3:
			type = Pet.DOG;
			break;
		default:
			type = Pet.OTHER;
			break;
		}
		System.out.println("Ingrese la edad de la mascota(Solo numeros)");
		age = Integer.parseInt(br.readLine());
		System.out.println("Ingrese el peso(kg) de la mascota(Solo numeros)");
		weight = Double.parseDouble(br.readLine());
		System.out.println("Ingrese la altura(m) de la mascota(Solo numeros)");
		height = Double.parseDouble(br.readLine());
		if (namePet.equals("") || type.equals("")) {
			System.out.println("Algun dato esta vacio. Intente de nuevo todo el procedimeinto");
			return false;
		}
		Pet newPet = new Pet(namePet, type, age, weight, height);
		auxClient.addPet(newPet);

		return true;
		}
		return false;
	}

	private void seePets(Client auxClient) {
		for (int i = 0; i < auxClient.getPets().size(); i++) {
			ArrayList<Pet> aux = auxClient.getPets();
			int index = i + 1;
			System.out.println(index + ". " + aux.get(i).getName());
		}

	}

	private void seeClients() {
		for (int i = 0; i < vetery.getClients().size(); i++) {
			ArrayList<Client> aux = vetery.getClients();
			int index = i + 1;
			System.out.println(index + ". " + aux.get(i).getName() + " ---> ID:" + aux.get(i).getId());
		}
	}

	private boolean RegisterClient() throws Exception {
		System.out.println("REGISTAR CLIENTE\n----------------------------------");
		String name = "";
		String id = "";
		String address = "";
		String tel = "";

		System.out.println("Ingrese el nombre del cliente");
		name = br.readLine();
		System.out.println("Ingrese el id del cliente(Es unico)");
		id = "";
		boolean out = false;
		do {
			id = br.readLine();
			Client aux = vetery.findClientByClientID(id);
			if (aux != null) {
				System.out.println("Este codigo ya exite. Intente otro");
				out = true;
			} else {
				out = false;
			}
		} while (out);
		System.out.println("Ingrese la direccion");
		address = br.readLine();
		if (name==null || id==null || address==null|| tel==null||name.equals("") || id.equals("") || address.equals("") || tel.equals("")) {
			System.out.println("Algun dato esta vacio. Intente de nuevo");
			return false;
		}
		System.out.println("------------------------------");
		String namePet = "";
		String type = "";
		int age = 0;
		double weight = 0;
		double height = 0;
		System.out.println("Mascota Inicial:\n");
		System.out.println("Ingrese el nombre de la mascota");
		namePet = br.readLine();
		System.out.println("Seleccione el tipo del animal");
		System.out.println("1. " + Pet.BIRD);
		System.out.println("2. " + Pet.CAT);
		System.out.println("3. " + Pet.DOG);
		System.out.println("4. " + Pet.OTHER);
		int select = Integer.parseInt(br.readLine());
		switch (select) {
		case 1:
			type = Pet.BIRD;
			break;
		case 2:
			type = Pet.CAT;
			break;
		case 3:
			type = Pet.DOG;
			break;
		default:
			type = Pet.OTHER;
			break;
		}
		System.out.println("Ingrese la edad de la mascota(Solo numeros)");
		age = Integer.parseInt(br.readLine());
		System.out.println("Ingrese el peso de la mascota(Solo numeros)");
		weight = Double.parseDouble(br.readLine());
		System.out.println("Ingrese la altura de la mascota(Solo numeros)");
		height = Double.parseDouble(br.readLine());
		if (type==null||namePet==null||namePet.equals("") || type.equals("")) {
			System.out.println("Algun dato esta vacio. Intente de nuevo todo el procedimeinto");
			return false;
		}
		Pet newPet = new Pet(namePet, type, age, weight, height);
		ArrayList<Pet> auxPet = new ArrayList<Pet>();
		auxPet.add(newPet);
		Client newClient = new Client(name, id, address, tel, auxPet);
		vetery.addClient(newClient);
		return true;
	}

}
