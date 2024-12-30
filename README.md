Multithreaded Array Processing in Java

Overview.
This project demonstrates how to use Java's ExecutorService for multithreading in order to process an array of integers. The program generates a random array of integers, splits the array into chunks, processes each chunk in separate threads, and combines the results.

Features.
Random Array Generation: Generates an array of random integers within a specified range.
Multithreading: Utilizes four threads to process the array in parallel.
Array Processing: Multiplies adjacent elements in the array and stores the results.
Result Compilation: Combines the results from all threads and prints the final output.
Execution Time Measurement: Measures the total execution time of the program.

Usage Prerequisites.
Java Development Kit (JDK) installed.

Running the Program.
Clone the repository or download the code.
Open a terminal and navigate to the directory containing the code.
Compile the code using javac Main.java.
Run the program using java Main.

Code Description.
The program generates an array with a random size between 40 and 60, filled with random integers within the range 0 to 100.
After the array is divided into chunks, and each chunk is processed in a separate thread. The ExecutorService manages the threads, and each thread multiplies adjacent elements in its chunk.
Result Compilation: Results from all threads are combined into a CopyOnWriteArraySet. The original array, processed results, and execution time are printed to the console.

Example Output.
Оригінальний масив: 
12 34 56 78 90 ...

Результат обчислень: 
408 4368 7020 ...

Час виконання програми: 10 мс

License
This project is licensed under the MIT License.
