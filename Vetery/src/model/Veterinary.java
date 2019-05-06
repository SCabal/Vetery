package model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Veterinary {
//atributes	
	private double earnings;
	private ArrayList<Client> clients;
	private Room[] rooms;
	private int openRoom;
	private ArrayList<MedRec> medRecs;
	private ArrayList<MedRec> medRecsClosed;
	private ArrayList<Service> services;
	private ArrayList<TypeService> typeServices;
	private ArrayList<Drug> listDrug;
//builder
	public Veterinary() throws Exception {
		earnings = 0;
		listDrug=new ArrayList<Drug>();
		
		
		clients = new ArrayList<Client>();
		medRecs = new ArrayList<MedRec>();
		medRecsClosed = new ArrayList<MedRec>();
		rooms = new Room[8];
		for (int i = 0; i < rooms.length; i++) {
			rooms[i] = new Room(i );
		}
		openRoom = 0;
		typeServices=new ArrayList<TypeService>();
		typeServices.add(new TypeService("Baño de mascotas en la veterinaria", 20000));
		typeServices.add(new TypeService("Baño de mascota a domicilio", 30000));
		typeServices.add(new TypeService("Corte de Uñas", 8000));
		typeServices.add(new TypeService("Profilaxis Dental", 12000));
		typeServices.add(new TypeService("Aplicación de Vacunas", 45000));
		services=new ArrayList<Service>();
		burnCode();
	}
//burnCode	
	private void burnCode() throws Exception {
		Pet pet1=new Pet("Tigra", Pet.DOG, 12, 15, 50);
		Pet pet2=new Pet("Draco", Pet.CAT, 10, 16, 30);
		ArrayList<Pet> nue = new ArrayList<Pet>();
		nue.add(pet1);
		nue.add(pet2);
		Client client1=new Client("Daniel", "123456789", "Calle 1", "255685", nue);
		Pet pet3=new Pet("Saria", Pet.CAT, 9, 18, 30);
		Pet pet4=new Pet("Link", Pet.DOG, 8, 10, 50);
		nue=new ArrayList<Pet>();
		nue.add(pet3);
		nue.add(pet4);
		Client client2=new Client("El pollo", "987654321", "Calle 2", "123488", nue);
		addClient(client1);
		addClient(client2);
		ArrayList<String> symptom=new ArrayList<String>();
		symptom.add("Diarrea");
		Drug nueva=new Drug("Paracetamol", "Una pastilla", 5500, 1);
		ArrayList<Drug> listDrugd=new ArrayList<Drug>();
		listDrugd.add(nueva);
		listDrug.add(nueva);
		addPetRoom("123456789", 1, symptom, "Esta enfermo", listDrugd, new Date());
		Date fecha=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String newLastModified = "01/31/1998";
		fecha = sdf.parse(newLastModified);
		medRecsClosed.add(new MedRec(client2, pet3, fecha , symptom, "Esta maluco", listDrugd, rooms[0]));
//getters		
	}	public double getEarnings() {
		return earnings;
	}
	public ArrayList<Client> getClients() {
		return clients;
	}
	public Room[] getRooms() {
		return rooms;
	}
	public int getOpenRoom() {
		return openRoom;
	}
	public ArrayList<MedRec> getMedRecs() {
		return medRecs;
	}
	public ArrayList<MedRec> getMedRecsClosed() {
		return medRecsClosed;
	}
	public ArrayList<Service> getServices() {
		return services;
	}
	public ArrayList<TypeService> getTypeServices() {
		return typeServices;
	}
	public ArrayList<Drug> getListDrug() {
		return listDrug;
	}
	public Client getClientPos(int index) {
		if(index>(clients.size()-1)) {
			return null;
		}
		return clients.get(index);
	}

//setters	
	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}

	public void setOpenRoom(int openRoom) {
		this.openRoom = openRoom;
	}

	public void setMedRecs(ArrayList<MedRec> medRecs) {
		this.medRecs = medRecs;
	}

	public void setMedRecsClosed(ArrayList<MedRec> medRecsClosed) {
		this.medRecsClosed = medRecsClosed;
	}

	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

	public void setTypeServices(ArrayList<TypeService> typeServices) {
		this.typeServices = typeServices;
	}

	public void setListDrug(ArrayList<Drug> listDrug) {
		this.listDrug = listDrug;
	}
//methods
	public void giveService(String id,int pet,TypeService serivice,Date today){
		Client aux = findClientByClientID(id);
		Date todayAux=new Date();
		if(today!=null) {
			todayAux=today;
		}
		services.add(new Service(serivice, todayAux, aux, aux.getPets().get(pet)));
	}
	public double calculateEarningsByServiceType(TypeService type) {
		double coutn=0;
		for (int i = 0; i < services.size(); i++) {
			Service aux=services.get(i);
			if(aux.getTypeSetService().getTypeService().equals(type.getTypeService())) {
				coutn+=(type.getCost());
			}
		}
		return coutn;
	}
	public double calculateEarningsTotal() {
		double total=earnings;
		for (int i = 0; i < typeServices.size(); i++) {
			total+=calculateEarningsByServiceType(typeServices.get(i));
		}
		return total;
	}
	public void addServiceType(String typeService,double cost) {
		typeServices.add(new TypeService(typeService, cost));
	}
	public double averageByServiceType(TypeService type) {
		double coutn=0;
		double avaran=0;
		for (int i = 0; i < services.size(); i++) {
			Service aux=services.get(i);
			if(aux.getTypeSetService().getTypeService().equals(type.getTypeService())) {
				coutn+=(type.getCost());
				avaran++;
			}
		}
		if(avaran==0) {
			return 0;
		}
		return coutn/avaran;
	}
	
	public double averageEarningsTotalWeek() {
		Date today=new Date();
		double count = 0;
		double sum = 0;
		for (int i = 0; i < medRecs.size(); i++) {
			int days = (int) ((today.getTime() - medRecs.get(i).getEnter().getTime()) / 86400000);
			if(days<=7&&days>0) {
				count++;
				sum+=medRecs.get(i).CostTotal();
			}
		}
		if(count==0) {
			return 0;
		}
		return  sum/count;
	}
	public String infoServiceInDateStartToDateEnd(Date start,Date end) {
		String info="";
		for (int i = 0; i < services.size(); i++) {
			Date aux=services.get(i).getDateReali();
			int dayStart = (int) ((aux.getTime() - start.getTime()) / 86400000);
			int dayEnd = (int) ((end.getTime() - aux.getTime()) / 86400000);
			if(dayEnd>0&&dayStart>0) {
				info+=services.get(i).getClient().getName()+" Con la mascota: "+services.get(i).getPet().getName()+"("+services.get(i).getPet().getType()+")-------> Servicio: "+services.get(i).getTypeSetService().getTypeService()+", Valor: "+services.get(i).getTypeSetService().getCost()+"\n";
			}
		}
		if(info.isEmpty()) {
			return "No existe informacion de estas fechas";
		}
		return info;
	}
	

	public boolean addPetRoom(String id, int pet, ArrayList<String> symptom, String diagnosis, ArrayList<Drug> drugs,Date today) {
		if (availabilityRoom()) {
			Client aux = findClientByClientID(id);
			if(aux==null) {
				return false;
			}
			Date todayAux=new Date();
			if(today!=null) {
				todayAux=today;
			}
			if(isHospitalized(aux,aux.getPets().get(pet))) {
				return false;
			}
			rooms[openRoom].setAvailability(false);
			medRecs.add(
					new MedRec(aux, aux.getPets().get(pet), todayAux, symptom, diagnosis, drugs, rooms[openRoom]));
			return true;
		}
		return false;

	}

	private boolean isHospitalized(Client client,Pet pet) {
		for (int i = 0; i < medRecs.size(); i++) {
			if(client.getId().equals(medRecs.get(i).getClient().getId())&&medRecs.get(i).getPet().getName().equals(pet.getName())){
				return true;
			}
		}
		return false;
	}
	public boolean availabilityRoom() {
	
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i].isAvailability()) {
				openRoom=i;
				return true;
			}
		}
		openRoom=-1;
		return false;
	}


	public void discharged(int indexMedRecs) {

		MedRec aux = medRecs.get(indexMedRecs);
		aux.setStatus(MedRec.CLOSE);
		aux.getRoom().setAvailability(true);
		earnings+=aux.CostTotal();
		medRecsClosed.add(aux);
		medRecs.remove(indexMedRecs);

	}
	
	
	public String infoPet(int indexMedRec){
		return medRecs.get(indexMedRec).toString() + "\n---------------------\nSintomas: "
				+ medRecs.get(indexMedRec).getSymptoms().toString() + "\nDiagnostico: " + medRecs.get(indexMedRec).getDiagnose()+"\n////////////////////////////////////////\n";
	}

	public String generateMedicalRec() {
		String info = "No hay informes actuales";
		if (medRecs.size() > 0) {
			info = "";
		}
		for (int i = 0; i < medRecs.size(); i++) {
			info += infoPet(i);
		}
		return info;
	}

	public Client findClientByPet(String namePed) {
		for (int i = 0; i < clients.size(); i++) {
			for (int j = 0; j < clients.get(i).getPets().size(); j++) {
				if(namePed.equals(clients.get(i).getPets().get(i).getName())) {
					return clients.get(i);
				}
			}
		}
		return null;
	}

	public Client findClientByClient(String name) {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getName().equals(name)) {
				return clients.get(i);
			}
		}
		return null;
	}

	public Client findClientByClientID(String id) {
		for (int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getId().equals(id)) {
				return clients.get(i);
			}
		}
		return null;
	}

	public String toString() {
		return "Mi pequeña mascota";
	}
	
	/**
	*Description This method allows to update the basic data of a veterinary client, these data include, address and phone number.
	*pre: The client was created before.
	*post: The address and /or phone number of the client is updated.
	*@param The new address of the client. This param could be empty.
	*@param The new phone number of the client. This param could be empty.
	*/
	public void editClient(int index,String newAddress,String newTel) {
		if(!newAddress.isEmpty()) {
			clients.get(index).setAddress(newAddress);
		}
		if(!newTel.isEmpty()) {
			clients.get(index).setTel(newTel);
		}
	}
	
	/**
	*Description This method allows to add new medicines that were prescription during the hospitalization at the patient stories.
	*pre: The patient clinic story must be not null.
	*post: New medicines were added to the patient clinic story.
	*@param The medicine name. This param must be not null.
	*@param The medicine dose, this param refers to the amount of medicine supplied to the pet each time according the frequence assigned.
	*@param The medicine cost by each dose. This param could be empty.
	*@param The frequency of medicine application. This param could be empty.
	*@return A message that indiques if medicine was added to the patient clinic story
	*/
	public String addDrugInMedRec(int index, ArrayList<Integer> listIndexDrug) {
		MedRec aux=medRecs.get(index);
		for (int i = 0; i < listIndexDrug.size(); i++) {
			aux.addDrug(listDrug.get(listIndexDrug.get(i)));			
		}
		return "La nueva medicina Fue Agregada";
	}
	
	/**
	*Description This method allows to add new notes to the possible diagnostic during the hospitalization at the patient stories.
	*pre: The patient clinic story must be not null.
	*post: New notes were added to the possible diagnostic in the patient clinic story.
	*@param The notes of possible diagnostic. This param must be not null.
	*/
	public void addNoteMedRec(int index, String noteDiagnostic) {
		medRecs.get(index).addNoteDiagnostic(noteDiagnostic);
	}
	
	/**
	*Description This method allows to add a new symptom presented during the hospitalization at the patient stories.
	*pre: The patient clinic story must be not null.
	*post: A new symptom were added to the patient clinic story.
	*@param The new symptom presented. This param must be not null.
	*/
	public void addSymtom(int index, String symptom) {
		medRecs.get(index).addSymtom(symptom);
	}
	public void addClient(Client newClient) {
		clients.add(newClient);
		
	}
	public String findPastHospitali(Client auxClient, int select) {
		// TODO Auto-generated method stub
		int count = 0;
		Pet auxPet=auxClient.getPets().get(select);
		for (int i = 0; i < medRecsClosed.size(); i++) {
			if(medRecsClosed.get(i).getPet().getName().equals(auxPet.getName())&&auxClient.getId().equals(medRecsClosed.get(i).getClient().getId())) {
				count++;
			}
		}
		if(count==0) {
			return "No ha sido hospitalizado antes";
		}
		return "Ha sido hospitalizado "+count+" veces";
	}
	
}
