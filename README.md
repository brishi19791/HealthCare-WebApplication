# HealthCare-WebApplication
Health care web application using Spring MVC, Hibernate, MySQL

In order to run the project, we have to create few useraccounts for login.

Should run java file present in below path which will create admin,doctor,nurse,lab assistant and pharmacist:
src\test\java\com\neu\project\Mapping.java

Flow of the Project:
======================
1) Admin can create doctor, nurse,lab assistant and pharmacist
2)Patient should register and select a primary doctor.
3)Nurse will take vitalSigns of the patient and send those to respective primary doctor
4)Doctor will see the vitalSigns of the patient and decides to diagnose or give medication to the patient.
	4.1) If doctor wants to diagnose he can send a work request to the lab assistant by selecting kind of lab test.
	4.2) If doctor wants to give medication, he can select a drug which should be manufactured by the pharmacist
5)Pharmacist will manufacture the drugs.
6)Lab assistant will get work request from the doctor to send the lab test reports for a patient.
7) Patient can login to see there encounterList, Lab test reports and medication provided by doctor.

