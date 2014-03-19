-- Legal queries
SELECT TIMEDIFF(DepartTime, ArrivalTime) from Visit; -- length of Visit
SELECT * from Visit WHERE <{where_condition}>; -- visit records
SELECT V_Procedure from Visit WHERE <{where_condition}>; -- procedures

-- Doctor queries
Select * from Appointment where DoctorID in (Select DoctorID from Doctor where Name = "Osama Bin Laden"); -- doctor's appointments
Select * from Visit where ApptID in (Select ApptID from Appointment where DoctorID in (Select DoctorID from Doctor where Name = "Kim Jong-un")) -- doctor's visits
INSERT INTO Patient(PatientID, SIN, HealthCardNo, HealthStatus, Phone, DefDoctorID, AuditTime, AuditByID) VALUES ('1', '123456789', '1234567890', 'Mentally Ill', '18003825672', '5', '2014-03-19 11:59:00', '5'  ); -- insert paitent
INSERT INTO HealthCard(HealthCardNo, Name, Address, DOB) VALUES ('1234567890', 'Edward Snowden', '123 Putin Boulevard, Russia', '1983-06-21 00:00:00'); -- insert health card
UPDATE Paitent SET SIN = 1234 WHERE <{where_condition}>;
UPDATE HealthCardNo SET Name = "Dick Butt" WHERE <{where_condition}>;

-- Staff queries
INSERT INTO Visit (ApptID, ArrivalTime, DepartTime, V_Procedure, Result, Prescription, V_Comment, AuditTime, AuditByID) VALUES (2, '2014-02-16 10:30:00', '2014-02-16 12:00:00', 'Lobotomy', 'Failure', '10cc Stolen Government Data', 'Paitent was not submissive. He ran off and defected to Russia', '2014-02-16 14:00:00', 5); -- add visit record
INSERT INTO Appointment (ApptID, DoctorID, PatientID, RoomNumber,ApptDate, ApptType, AuditTime, AuditByID) VALUES (1, 5, 1, 43, '2014-02-16 10:00:00', 'Surgery', '2014-01-19 12:10:00', 5); -- add appointment
UPDATE Appointment SET ApptID = 1, DoctorID = 5, PatientID = 1, RoomNumber = 43, ApptDate = '2014-02-16 10:00:00', ApptType = 'Surgery', AuditTime = '2014-01-19 12:10:00', AuditByID = 5 WHERE <{where_condition}>; -- change appointment
UPDATE Visit SET ApptID = 2, ArrivalTime = '2014-02-16 10:30:00', DepartTime = '2014-02-16 12:00:00', V_Procedure = 'Lobotomy', Result = 'Failure', Prescription = '10cc Stolen Government Data', V_Comment = 'Paitent was not submissive. He ran off and defected to Russia', AuditTime = '2014-02-16 14:00:00', AuditByID = 5 WHERE <{where_condition}>; -- change visit

-- Financial queries
SELECT * from Doctor where Name = "Dick Butt";
Select * from Visit where TIMEDIFF_SECS(DepartTime, Arrival) > 1000;
Select * from Paitent where HealthCardNo in (Select HealthCardNo from HealthCard where Name = "Dick Butt");

-- Paitent queries
Select * from Paitent where PaitentID in (Select LoginID from Login where Name = "username");
Select * from Paitent where HealthCardNo in (Select HealthCardNo from HealthCard where Name = "Dick Butt")
Select * from Visit where ApptID in (Select ApptID from Appointment where PaitentID in (Select LoginID from Login where Name = ""));
