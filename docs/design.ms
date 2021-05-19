.blm .

.nr GROWPS 3

.TL
Graphing Calculator - Design Document

.AU
Kian Kasad

.SH 1
User Interface

.SH 2
Screen design

.LP
The following is a representation of the sections of the screen:
.TS
expand allbox ;
cx c
c s.
equation input text box	\(lqGraph\(rq button
T{
.sp
.sp
.sp
.sp
.sp
.sp
.sp
.sp
.sp
.sp
graph panel
.sp
.sp
.sp
.sp
.sp
.sp
.sp
.sp
.sp
.sp
T}
.TE
The user can input their equation in the text box. Then when they press the
\(lqGraph\(rq button, their equation will be graphed in the graph panel.

.KS
.SH 1
Module Diagrams & Responsibilities

.SH 2
Inheritance Diagram
.LP
.PS
JF: box "JFrame" ht 0.7 wid 1.3 at (-1, 1)
JP: box "JPanel" ht 0.7 wid 1.3 at (1, 0)
ML: box "MouseListener" ht 0.7 wid 1.3 at (1, 1)
MML: box "MouseMotionListener" ht 0.7 wid 1.3 at (2.5, 1)
MWL: box "MouseWheelListener" ht 0.7 wid 1.3 at (4, 1)
GC: box "GraphingCalculator" ht 0.7 wid 1.3 at (-1, 0)
GP: box "GraphPanel" ht 0.7 wid 1.3 at (2.5, 0)
arrow from GC.n to JF.s
arrow from GP.w to JP.e
arrow from GP.nw to ML.se
arrow from GP.n to MML.s
arrow from GP.ne to MWL.sw
.PE

.SH 2
Program Layout Diagram
.LP
.PS
GC: box "GraphingCalculator" ht 0.7 wid 1.5 at (0, 3)
GP: box "GraphPanel" ht 0.7 wid 1.5 at (0, 1.5)
EQ: box "Equation" ht 0.7 wid 1.5 at (-1, 0)
PO: box "Point" ht 0.7 wid 1.5 at (1, 0)
CO: box "Color" ht 0.7 wid 1.5 at (-2, 1.5)
EX: box "Expression" "\fI(exp4j)\fP" ht 0.7 wid 1.5 at (-3, 0)
arrow dashed from GC.s to GP.n
arrow dashed from GP.s to EQ.n
arrow dashed from GP.s to PO.n
arrow dashed from GP.w to CO.e
arrow dashed from EQ.w to EX.e
.PE
Dashed arrows represent a
.I HAS-A
relationship. E.g.
.CW GraphingCalculator
has a
.CW GraphPanel ,
which has a
.CW Color .

.SH 2
Responsibilities
.LP
I'll be writing everything that needs to be implemented, since I'm working
alone. I'll be using the
.pdfhref W -D "https://lallafa.objecthunter.net/exp4j/" -- exp4j
equation parsing library to parse the user's equation into a computable format.
.KE

.KS
.SH 1
Tests

.SH 2
Test Equations
.LP
.TS
lb lb lb
n l l .
Case	Equation	Result*
1	\fCy = x\fP	Diagonal line with slope of 1, passing through (0, 0)
2	\fCy = -x/2\fP	Diagonal line with slope of -\(12, passing through (0, 0)
3	\fCy = sin(x)\fP	Sine wave with amplitude of 1 and period of 2\(*p
4	\fCy = x^3\fP	Cubic equation that increases in range (-\(if, \(if)
5	\fCy = e^x\fP	Standard exponential growth graph
6	\fCy = g\fP	Invalid equation alert should show, since variable \fCg\fP is not defined
.TE
*the result must be checked visually, probably by comparing to a known correct
graphing calculator.

.SH 2
Mouse input tests
.LP
.TS
lb lb lb
n l l .
Case	Input	Result
1	Scroll down	Graph zooms out
2	Scroll up	Graph zooms in
3	Press mouse on graph line and hold	Tooltip appears
4	Press mouse on graph line and move it around	Tooltip appears and moves
5	Release mouse button	Tooltip disappears
.TE
.KE
