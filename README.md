Hack #75 from Swing Hacks book
--

This is a significant reworking of the example code for Hack #75, Build an Audio Waveform Display
from the book [Swing Hacks: Tips & Tools for Building Killer GUIs](http://www.oreilly.com/catalog/swinghks/)
by [Joshua Marinacci](https://twitter.com/joshmarinacci) & [Chris Adamson](https://twitter.com/invalidname),
ISBN [978-0-596-00907-6](https://www.worldcat.org/search?q=bn%3A978-0-596-00907-6).

This particular hack appears to have been originally authored by Jonathan Simon, who also contributed to the book.

The original example code is available from O'Reilly's git repo: 
https://resources.oreilly.com/examples/9780596009076/tree/master/Ch10-Audio/75

### What's different?
* Making use of Java 7 features
* More narrow exception handling
* Using a JFileChooser dialog instead of command line args to select a file to view.
* More intuitive naming of variables, methods, and classes throughout
* Reworked the convoluted technique for reading audio files
* The method to draw the waveform is largely different, both
eliminating code that served no purpose and scaling the y axis in a more meaningful way.

### Build
Run ```mvn package``` to compile and produce the artifact.

### Run
Run ```java -jar target/swing-hacks-75-0.1.0.jar``` to start the program.

### Sample output

This is the waveform for the classic [Windows 3.1 chimes.wav](https://www.youtube.com/watch?v=nH3y5_t3JRw), in a narrow window:

![narrow waveform](blob/master/src/site/resources/images/chimes-narrow.png)

and the same window after being stretched wider:

![wide waveform](blob/master/src/site/resources/images/chimes-wide.png)
