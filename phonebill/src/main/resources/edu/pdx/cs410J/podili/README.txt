Name of the Assignment: Project2

Author: Goutham Podili

Usage: java edu.pdx.cs410J.<login-id>.Project2 [options] <args>.
args are in this order customer name, caller number, callee number, start time, endtime.
options are -README, -print. -textFile file

Description: This project recieves customer name, Caller number, Callee number,
Call start date, Call end date from the command line and validates the inputs recieved
and prints them to the console when -print option is specified.
-README gives description about the project.
when -textFile file is specified it checks the file and if it does not exists
it creates a new file and add phonebill to it. If files exists and is empty it will throw
an error. If files exists and file is not empty it checks the phonebill objects in the file
if objects are not valid it throws an exception if objects are valid it adds object to the file.

