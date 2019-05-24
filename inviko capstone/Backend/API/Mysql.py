import peewee
import MySQLdb
from peewee import SQL
import datetime
import json
import Credentials

class DatabaseConfig(object):
    db = peewee.MySQLDatabase("sql3276761", host= "sql3.freemysqlhosting.net", port=3306, user="sql3276761",passwd="GVIuzlTjCA")


class admin(peewee.Model):
    _id     	= peewee.PrimaryKeyField()
    aname  	    = peewee.CharField()
    apass  	    = peewee.CharField()
    profile  	    = peewee.CharField()

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(admin, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db

#System Main tables:-
#Module A
class Role(peewee.Model):
    role_id     	= peewee.PrimaryKeyField()
    Role_Name  	    = peewee.CharField()
    status      	= peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Role, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db



class User(peewee.Model):
    user_id     	= peewee.PrimaryKeyField()
    First_Name  	= peewee.CharField()
    Last_Name		= peewee.CharField()
    Email    	    = peewee.CharField()
    Password  	    = peewee.CharField(null = True)
    Address1    	= peewee.CharField()
    Address2    	= peewee.CharField()
    City    	    = peewee.CharField()
    Postalcode    	= peewee.CharField()
    Province    	= peewee.CharField()
    Height    	    = peewee.CharField()
    Weight    	    = peewee.CharField()
    Age    	        = peewee.CharField()
    Contact_Number 	= peewee.CharField()
    role            = peewee.CharField()
    status      	= peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(User, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db

class Dashboard_Notification(peewee.Model):
    dasboard_notify_id     	= peewee.PrimaryKeyField()
    Notification  	        = peewee.CharField()
    role                    = peewee.CharField()
    status      	        = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Dashboard_Notification, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db

class Dashboard_Annoucement(peewee.Model):
    dasboard_annouce_id     = peewee.PrimaryKeyField()
    annoucement  	        = peewee.CharField()
    role                = peewee.CharField()
    status      	        = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Dashboard_Annoucement, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db

class Dashboard_Schedule(peewee.Model):
    dasboard_schedule_id     	= peewee.PrimaryKeyField()
    schedule  	                = peewee.CharField()
    role                        = peewee.CharField()
    status      	            = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Dashboard_Schedule, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db


class Doctor(peewee.Model):
    doctor_id     	                    = peewee.PrimaryKeyField()
    doctor_firstname  	                = peewee.CharField()
    doctor_lastname                     = peewee.CharField()
    doctor_speciality                   = peewee.CharField()
    doctor_phone                        = peewee.CharField()
    status      	                    = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Doctor, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db


class Patient(peewee.Model):
    patient_id     	                    = peewee.PrimaryKeyField()
    patient_firstname  	                = peewee.CharField()
    patient_lastname                    = peewee.CharField()
    patient_email                       = peewee.CharField()
    patient_address_1                   = peewee.CharField()
    patient_address_2                   = peewee.CharField()
    patient_postalcode                  = peewee.CharField()
    patient_province                    = peewee.CharField()
    phone_no                            = peewee.CharField()
    patient_age                         = peewee.CharField()
    patient_height                      = peewee.CharField()
    patient_weight                      = peewee.CharField()
    doctor_id                           = peewee.ForeignKeyField(Doctor, to_field="doctor_id")
    status      	                    = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Patient, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db


class Availablity(peewee.Model):
    availablity_id     	               = peewee.PrimaryKeyField()
    doctor_id  	                       = peewee.ForeignKeyField(Doctor, to_field="doctor_id")
    date                               = peewee.CharField()
    time                               = peewee.CharField()
    status      	                   = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Availablity, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db



class Appointment(peewee.Model):
    appointment_id     	               = peewee.PrimaryKeyField()
    doctor_id  	                       = peewee.ForeignKeyField(Doctor, to_field="doctor_id")
    patient_id  	                   = peewee.ForeignKeyField(Patient, to_field="patient_id")
    availablity_id  	               = peewee.ForeignKeyField(Availablity, to_field="availablity_id")
    status      	                   = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Appointment, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db

class Appointment_Demo(peewee.Model):
    appointment_id     	               = peewee.PrimaryKeyField()
    doctor_id  	                       = peewee.ForeignKeyField(Doctor, to_field="doctor_id")
    patient_id  	                   = peewee.ForeignKeyField(Patient, to_field="patient_id")
    availablity_date  	               = peewee.CharField()
    availablity_time  	               = peewee.CharField()
    status      	                   = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Appointment_Demo, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db

class Diagnosis(peewee.Model):
    diagnosis_id     	               = peewee.PrimaryKeyField()
    patient_id  	                   = peewee.IntegerField()
    doctor_id  	                       = peewee.IntegerField() 
    appointment_id  	               = peewee.IntegerField()
    diagnosis                          = peewee.CharField()
    prescription                       = peewee.CharField()
    reports                            = peewee.CharField()                        
    status      	                   = peewee.IntegerField(default = 1)

    def save(self, *args, **kwargs):
        self.modify_date = datetime.datetime.strptime(datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S'), '%Y-%m-%d %H:%M:%S')
        return super(Appointment, self).save(*args, **kwargs)

    class Meta:
        database = DatabaseConfig.db

DatabaseConfig.db.create_tables(
    [
        User,
        Role,
        Dashboard_Notification,
        Dashboard_Annoucement,
        Dashboard_Schedule,
        admin,
        Doctor,
        Patient,
        Availablity,
        Appointment,
        Diagnosis,
        Appointment_Demo
    ],
safe=True)
DatabaseConfig.db.close()