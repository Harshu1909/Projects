# InViKo
# Created by Harsh Patel on 2019-01-25
# FLASK API WITH MYSQL AS A BACKEND

from flask import Flask,request,jsonify
from flask_mysqldb import MySQL
import Credentials
from flask_json import FlaskJSON, JsonError, json_response, as_json
import json
from Mysql import *



# Declarations 
mysql = MySQL()
app = Flask(__name__)

Credentials
app.config['MYSQL_HOST'] = Credentials.Server
app.config['MYSQL_USER'] = Credentials.Username
# app.config['MYSQL_PORT'] = 3306
app.config['MYSQL_PASSWORD'] = Credentials.Password
app.config['MYSQL_DB'] = Credentials.Name
mysql.init_app(app)

def raise_error(status_code, message):
    response = jsonify({
        'status': status_code,
        'message': message
    })
    response.status_code = status_code
    return response

# Start APIs
# API for Check MySQL Connection
@app.route('/CheckDatabase')
def SqlConnection():
	cursor = mysql.connection.cursor()
	conn = mysql.connection
	return str(conn)


@app.route('/')
def index():
	cursor = mysql.connection.cursor()
	cursor.execute('''SELECT * FROM User''')
	rv = cursor.fetchall()
	json_data=[]
   	for result in rv:
		   json_data.append(result)
   	return json.dumps(json_data)

@app.route('/api/get_role',methods = ['GET'])
def get_role():
	try:
		cursor = mysql.connection.cursor()
		cursor.execute('''SELECT * FROM role''')
		rv = cursor.fetchall()
		json_data={}
		json_array=[]
   		for result in rv:
			   json_data = {"id":result[0],"text":result[1]}
			   json_array.append(json_data)
   		return json.dumps(json_array[0])

	except Exception as e:
		error="Error in get_roles(),views.api: "+str(e)
        #log(error)
        print(error)
        return raise_error(500, error)


@app.route('/api/get_notification',methods = ['POST'])
def get_notification():
	try:
		cursor = mysql.connection.cursor()
		response = request.json
		Role = request.json.get('Role')
		cursor.execute('''SELECT Notification FROM dashboard_notification WHERE role = %s''',(Role,))
		rv = cursor.fetchall()
		json_data={}
		json_array=[]
   		for result in rv:
			   json_data = {"text":result[0]}
			   json_array.append(json_data)
   		return json.dumps({"Notifications" : json_array})

	except Exception as e:
		error="Error in get_notification\(),views.api: "+str(e)
        #log(error)
        print(error)
        return raise_error(500, error)


@app.route('/api/get_annoucement',methods = ['POST'])
def get_annoucement():
	try:
		cursor = mysql.connection.cursor()
		response = request.json
		Role = request.json.get('Role')
		cursor.execute('''SELECT annoucement FROM dashboard_annoucement WHERE role = %s''',(Role,))
		rv = cursor.fetchall()
		json_data={}
		json_array=[]
   		for result in rv:
			   json_data = {"text":result[0]}
			   json_array.append(json_data)
   		return json.dumps({"Annoucement" : json_array})

	except Exception as e:
		error="Error in get_annoucement\(),views.api: "+str(e)
        #log(error)
        print(error)
        return raise_error(500, error)



@app.route('/api/register', methods = ['POST'])
def register():
	cursor = mysql.connection.cursor()
	response = request.json
	Firstname = request.json.get('Firstname')
	Lastname = request.json.get('Lastname')
	Username = request.json.get('Username')
	Email = request.json.get('Email')
	Password = request.json.get('Password')
	ConfrimPassword = request.json.get('ConfrimPassword')
	Address1 = request.json.get('Address1')
	Address2 = request.json.get('Address2')
	City = request.json.get('City')
	Postalcode = request.json.get('PostalCode')
	Province = request.json.get('Province')
	Height = request.json.get('Height')
	Weight = request.json.get('Weight')
	Age = request.json.get('Age')
	Contactnumber = request.json.get('Number')
	Role = request.json.get('Role')
	cursor.execute('''INSERT INTO user (First_Name, Last_Name, Email, Password, Address1, Address2, City, Postalcode, Province, Height, Weight, Age, Contact_Number,Role) VALUES
					(%s, %s, %s, %s, %s, %s,%s,%s,%s,%s,%s,%s,%s,%s)''',(Firstname,Lastname,Email,Password, Address1, Address2, City, Postalcode, Province, Height, Weight, Age, Contactnumber, "Nurse"))
	mysql.connection.commit()
	data = {"Status":"200","Message":"Register Successful"} # Your data in JSON-serializable type
	response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 	return response

@app.route('/api/login', methods = ['POST'])
def login():
	cursor = mysql.connection.cursor()
	response = request.json
	Username = request.json.get('Email')
	Password = request.json.get('Password')
	cursor.execute('''SELECT COUNT(1) FROM user WHERE Email = %s  AND Password = %s''',(Username,Password))
	if not cursor.fetchone()[0]:
		data = {"Status":"405","Message":"Unautorized User"} # Your data in JSON-serializable type
		response = app.response_class(response = json.dumps(data),status = 401,mimetype = 'application/json')
 		return response

	else:
		data = {"Status":"200","Message":"Login Successful"} # Your data in JSON-serializable type
		response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 		return response


@app.route('/api/add_patient', methods = ['POST'])
def add_patient():
	cursor = mysql.connection.cursor()
	response = request.json
	patient_firstname = request.json.get('PatientFirstName')
	patient_lastname = request.json.get('PatientLastName')
	patient_email = request.json.get('PatientEmail')
	patient_address_1 = request.json.get('PatientAdd1')
	patient_address_2 = request.json.get('PatientAdd2')
	patient_postalcode = request.json.get('PostalCode')
	patient_province = request.json.get('Province')
	phone_no = request.json.get('Phone')
	patient_age = request.json.get('PatientAge')
	patient_height = request.json.get('PatientHeight')
	patient_weight = request.json.get('PatientWeight')
	doctor_id = request.json.get('DoctorId')
	
	cursor.execute('''INSERT INTO patient (patient_firstname, patient_lastname, patient_email, patient_address_1, patient_address_2, patient_postalcode, patient_province, phone_no, patient_age, patient_height, patient_weight, doctor_id) VALUES
					(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)''',(patient_firstname,patient_lastname,patient_email,patient_address_1,patient_address_2,patient_postalcode,patient_province,phone_no,patient_age,patient_height,patient_weight,doctor_id))
	mysql.connection.commit()
	data = {"Status":"200","Message":"Patient Register Successful"} # Your data in JSON-serializable type
	response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 	return response

@app.route('/api/add_availablity', methods = ['POST'])
def add_availablity():
	cursor = mysql.connection.cursor()
	response = request.json
	doctor_id = request.json.get('Doctor_id')
	date = request.json.get('Date')
	time = request.json.get('Time')
	
	cursor.execute('''INSERT INTO availablity (doctor_id, date, time) VALUES
					(%s, %s, %s,)''',(doctor_id,date,time))
	mysql.connection.commit()
	data = {"Status":"200","Message":"Availablity added"} # Your data in JSON-serializable type
	response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 	return response


@app.route('/api/add_appointment', methods = ['POST'])
def add_appointment():
	cursor = mysql.connection.cursor()
	response = request.json
	patient_id = request.json.get('Patient_id')
	doctor_id = request.json.get('Doctor_id')
	time = request.json.get('Time')
	date = request.json.get('Date')

	
	cursor.execute('''INSERT INTO appointment_demo (doctor_id, patient_id, availablity_date, availablity_time) VALUES
					(%s, %s, %s, %s)''',(doctor_id,patient_id,date,time))
	mysql.connection.commit()
	data = {"Status":"200","Message":"Patient Register Successful"} # Your data in JSON-serializable type
	response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 	return response


@app.route('/api/get_appointment', methods = ['POST'])
def get_appointment():
	cursor = mysql.connection.cursor()
	response = request.json
	doctor_id = request.json.get('Doctor_id')
	patient_id = request.json.get('Date')
	availablity_id = request.json.get('Time')
	
	cursor.execute('''INSERT INTO appointment (doctor_id, patient_id, availablity_id) VALUES
					(%s, %s, %s,)''',(doctor_id,patient_id,availablity_id))
	mysql.connection.commit()
	data = {"Status":"200","Message":"Appointment Added"} # Your data in JSON-serializable type
	response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 	return response

@app.route('/api/add_diagnosis', methods = ['POST'])
def add_diagnosis():
	cursor = mysql.connection.cursor()
	response = request.json
	doctor_id = request.json.get('Doctor_id')
	patient_id = request.json.get('Patient_id')
	appointment_id = request.json.get('Appointment_id')
	diagnosis = request.json.get('Diagnosis_name')
	prescription = request.json.get('Prescription')
	report = request.json.get('Reports')
	
	cursor.execute('''INSERT INTO diagnosis (doctor_id, patient_id,appointment_id, diagnosis, prescription, reports) VALUES
					(%s, %s, %s, %s, %s, %s)''',(doctor_id, patient_id,appointment_id, diagnosis, prescription, report))
	mysql.connection.commit()
	data = {"Status":"200","Message":"Diagnosis Added"} # Your data in JSON-serializable type
	response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 	return response


@app.route('/api/list_patients',methods=['GET'])
def list_patients():
    try:
        list_patient_squery = Patient.select(
			Patient.patient_id,
            Patient.patient_firstname,
            Patient.patient_lastname,
            Patient.phone_no,
			Patient.patient_age,
			Patient.patient_email,
        ).dicts()
       

        list_patient = []
        for data in list_patient_squery:
            list_patient.append(data)
        return json.dumps({"status":200,"List_Patients":list_patient})
    except Exception as e:
        error="Error in list_patient_list(),views.api: "+str(e)
        # log(error)
        return raise_error(500, error)

@app.route('/api/list_appointment',methods=['GET'])
def list_appointment():
    try:
        list_appointment_squery = Appointment_Demo.select(
			Appointment_Demo.appointment_id,
            Appointment_Demo.patient_id,
            Appointment_Demo.doctor_id,
            Appointment_Demo.availablity_date,
			Appointment_Demo.availablity_time,
			Doctor.doctor_firstname,
			Doctor.doctor_lastname,
			Patient.patient_firstname,
			Patient.patient_lastname
        ).join(Doctor).join(Patient).where(Appointment_Demo.appointment_id == Appointment_Demo.appointment_id,
		Appointment_Demo.doctor_id == Doctor.doctor_id,Appointment_Demo.patient_id == Patient.patient_id).order_by(Appointment_Demo.availablity_date).dicts()
       

        list_patient = []
        for data in list_appointment_squery:
            list_patient.append(data)
        return json.dumps({"status":200,"List_Appointment":list_patient})
    except Exception as e:
        error="Error in list_appointment(),views.api: "+str(e)
        # log(error)
        return raise_error(500, error)

@app.route('/api/list_doctor',methods=['POST'])
def list_doctor():
    # try:
		response = request.json
		patient_id = request.json.get("Email")
		list_doctor_squery = Doctor.select(
			Doctor.doctor_id,
			Doctor.doctor_firstname,
			Doctor.doctor_lastname
		).dicts()
		list_doctor = []
		for data in list_doctor_squery:
			list_doctor.append(data)
		return json.dumps({"status":200,"List_Doctor":list_doctor})
	# except Exception as e:
	# 	error = "Error in List_Doctor" +str(e)
	# 	return raise_error(500,error)


@app.route('/api/Patient_Details_by_id', methods = ['POST'])
def Patient_Details_by_id():
	try:
		response = request.json
		patient_id = request.json.get('Patient_id')
		list_patient_squery = Patient.select(
			Patient.patient_id,
            Patient.patient_firstname,
            Patient.patient_lastname,
			Patient.patient_email,
            Patient.phone_no,
        ).where(Patient.patient_id == patient_id).dicts()
		list_patient = []
		for data in list_patient_squery:
			list_patient.append(data)
		return json.dumps({"status":200,"Patients_Details":list_patient})
	except Exception as e:
		error="Error in Patient_Details_by_id(),views.api: "+str(e)
		return raise_error(500, error)


@app.route('/api/delete_appointment', methods = ['POST'])
def delete_appointment():
	try:
		response = request.json
		Appointment_id = request.json.get('Appointment_id')
		Delete_Appointment = Appointment_Demo.delete().where(Appointment_Demo.appointment_id == Appointment_id)
		Delete_Appointment.execute()
		data = {"Status":"200","Message":"Appointment Deleted"} # Your data in JSON-serializable type
		response = app.response_class(response = json.dumps(data),status = 200,mimetype = 'application/json')
 		return response
	except Exception as e:
		error="Error in delete_appointment(),views.api: "+str(e)
		return raise_error(500, error)
#End APIs
if __name__ == '__main__':
	app.run(debug=True)




