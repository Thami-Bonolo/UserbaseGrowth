# UserbaseGrowth
# FoondaMateCodingChallange
## FoondaMate Software Engineer Coding Challange

This is a CLI program. And the goal was to make everything run directly on terminal. This is why I used python3 plotext to plot the graph

The project was created using __Java__ and __Python3__.

__Java__: For accessing and pulling data from site.
Also used for running the project.

__Python3__: For sketching the graph.
> library used in Python3 is: _plotext_

_The python3 code will run automatically when java has finished to process the data and saved it into a .txt file. This .txt file is only temporary,
it gets deleted after the program executes_

How to run the program:  
_There are a number of ways you can run the program_  
__1. Command Line__  
open the cmd and traverse to the directory (`cd /src/main/java/`) with the program and run the command:

```
javac CodingChallange.java   
javac Main.java
```
For compiling the CodingChallange.java class
```    
java Main
```
For runnig the program.
This will run the program with all the data dates.
    
If you want to run for a given range then you have to pass the start date and end date in the arguments like,
```
java Main "03 01 2022" "07 01 2022"
```
  i.e Dates have to be in this format, `dd MM yyyy`

<sub>For this option you will need the __Graph.py__ to be in the same folder as the .java files. Hence the two files in two locations </sub>

__2. Using IDE__  
<sub>The project was made with __NetBeans 12.6__ </sub>    
You can import the project into your IDE, compile then run.
