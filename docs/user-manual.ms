.blm .

.nr GROWPS 3

.TL
Graphing Calculator - User Manual

.AU
Kian Kasad

.NH 1
Product Description
.LP
The Graphing Calculator is designed to create and display graphs of
mathematical equations.

.NH 1
System requirements
.LP
A Java Runtime Environment (JRE) version 8 or later is required. If you do not
have a JRE installed, you can download one from
.pdfhref W -D "https://java.com/en/download/manual.jsp" -A . -- the Java website
The device
running the program must also have a graphical interface. The Graphing
Calculator cannot be used from a console.

.NH 1
Usage Instructions

.NH 2
Running the Graphing Calculator
.LP
The Graphing Calculator is provided as a Java archive (JAR) executable. How to
run this JAR differs depending on your computer's operating system, but it can
generally be done using the following command:
.IP
\fC
java -jar GraphingCalculator.jar
\fP
.LP
Some operating systems which include a file manager also allow you to launch
the program by simply double-clicking on the program from within the file
manager.

.NH 2
Using the Graphing Calculator

.LP
When the Graphing Calculator starts, it will look as follows:
.PSPIC -C img/start-screen.ps
.LP
It graphs the following example equation, which is shown in the text box at the
top of the screen:
.IP
.CW "y = sin(x)"
.LP
This represents a sine wave, which is displayed in the graph panel.

.NH 3
Interacting with the graph

.SH 4
Zooming in/out
.LP
The Graphing Calculator supports zooming in and out using the scroll wheel on a
mouse. Scroll up to zoom in and scroll down to zoom out.

.KS
.SH 4
Tooltips
.LP
You can inspect the exact value of the graph at a certain location by clicking
on the line. While the mouse is pressed, a tooltip will appear, displaying the
x/y coordinates at the point where your mouse is. You can also click and drag,
and the tooltip will follow your mouse cursor.
.LP
The tooltip looks like this:
.PSPIC -L img/tooltip.ps
.KE

.NH 3
Changing the equation
.LP
Changing the equation is simple. Just type your equation in the text box at the
top, then click the
.BI Graph
button to the right. See
.pdfhref L -D s4 -- Section 4
for equation syntax.

.NH 1
.pdfhref M s4
Equation syntax

.NH 2
Whitespace
.LP
Whitespace is optional everywhere in the equation. It will be ignored. For
example, the following equations are all identical:
.IP
\fC\
y=x
.br
y = x
.br
y= x
.br
y =x
.br
y                  =             x
\fP\

.NH 2
Equation vs. Expression
.LP
The equation can be represented as either an equation or an expression. If it
is represented as an equation, it must start with
.CW "y = " \(rq. \(lq
If it is represented as an expression, it is assumed that the equation is
.CW "y = <expression>" \(rq. \(lq
.LP
The following examples are treated identically:
.IP
\fC\
y = sin(2 pi x)
.br
sin(2 pi x)
\fP\

.NH 2
Variables & Symbols
.LP
The following variables are defined and can be used in your equation:
.TS
lb lb lb
l l l .
Variable	Constant	Value
\fCx\fP	no	current value on the x-axis
\fCpi\fP	yes	value of \(*p (specifically Java's \fCMath.PI\fP constant)
\fC\(*p\fP	yes	value of \(*p (same as the variable \fCpi\fP)
\fCe\fP	yes	value of Euler's number \fIe\fP (specifically Java's \fCMath.E\fP constant)
\fC\(*f\fP	yes	value of the golden ratio
.TE

.KS
.NH 2
Arithmetic operators
.LP
The following operators are available:
.TS
lb lb lb lb
l l l l .
Operator	Operation	Example	Mathematical equavalent of example
\fC*\fP	Multiplication	\fCx * y\fP	\fIx\fP \[tmu] \fIy\fP
\fC+\fP	Addition	\fCx + y\fP	\fIx\fP \(pl \fIy\fP
\fC-\fP	Addition	\fCx - y\fP	\fIx\fP \(mi \fIy\fP
\fC/\fP	Division	\fCx / y\fP	\fIx\fP \(di \fIy\fP
\fC^\fP	Exponentiation	\fCx ^ y\fP	\fIx\fP\*{\fIy\fP\*}
\fC%\fP	Modulo	\fCx % y\fP	\fIx\fP % \fIy\fP \fBor\fP \fIx\fP mod \fIy\fP
\fC-\fP	Negation	\fC-x\fP	-\fIx\fP (negative \fIx\fP)
.TE
.KE
.BI Note:
the unary minus operator has lower precedence than the exponentiation operator, so
.CW "-3^x"
is the same as
.CW "-(3^x)" ,
.BI not
.CW "(-3)^x" .

.NH 2
Functions
.LP
Functions use the following syntax:
.IP
.CW "func(expr)"
to perform function
.CW func
on
.CW expr .
.LP
The following functions are defined:
.TS
lb lb
l l .
Function	Description
\fCabs()\fP	absolute value
\fCacos()\fP	arc cosine
\fCasin()\fP	arc sine
\fCatan()\fP	arc tangent
\fCcbrt()\fP	cubic root
\fCceil()\fP	nearest upper integer
\fCcos()\fP	cosine
\fCcosh()\fP	hyperbolic cosine
\fCexp()\fP	euler's number raised to the power (e^x)
\fCfloor()\fP	nearest lower integer
\fClog()\fP	natural logarithm (base e)
\fClog10()\fP	logarithm (base 10)
\fClog2()\fP	logarithm (base 2)
\fCsin()\fP	sine
\fCsinh()\fP	hyperbolic sine
\fCsqrt()\fP	square root
\fCtan()\fP	tangent
\fCtanh()\fP	hyperbolic tangent
\fCsignum()\fP	signum function
.TE

.KS
.NH 2
Scientific Notation
.LP
Scientific notation can be used to represent numbers. The format is
.CW yEx
or
.CW yex ,
where
.CW y
and
.CW x
are numbers. This will be interpreted as
.CW "y * 10^x" .
For example,
.CW "1.1e3"
is equivalent to
.CW "1.1 * 10^3" ,
or
.CW "1100" .
.LP
However, this
.CW E
is not an operator. It only works on number literals, not expressions. So
.CW "1.1E-(2x)"
will not work.
.KE

.KS
.NH 2
Implicit multiplication
.LP
Implicit multiplication is supported. This means you can place multiple
variables, functions, and/or numbers next to each other without multiplication
signs in between, and it will be interpreted as multiplying all the terms
together. For example, each row in the following table contains identical
expressions:
.IP
.TS
lb lb
l l .
Explicit	Implicit
\fC6 * cos(4 * \(*p * x)	6cos(4\(*px)\fP
\fC2 * x	2x\fP
\fC4 * pi	4pi\fP
\fC7 * sqrt(18 * x)	7sqrt(18x)\fP
.TE
.KE

.KS
.NH 2
Invalid equations
.LP
If you enter an invalid equation, you'll get an error message that describes the problem:
.PSPIC img/error.ps
.KE

.NH 2
Example equations
.LP
Here's a list of a few valid equations:
.IP
\fC\
y = x
.br
y = x^2
.br
y = e^(6cos(x))
.br
y = 15x^5 + 10x^4 + 5x^3 + x^2 + -7x - 1829
.br
y = -2
\fP\

.NH 1
Open Source Licenses
.LP
The Graphing Calculator program uses the following libraries:
.IP
.TS
lb lb lb
l l l .
Name	Author	License
T{
.pdfhref W -D "https://lallafa.objecthunter.net/exp4j/" -- exp4j
T}	Frank Asseg	T{
.pdfhref W -D "https://lallafa.objecthunter.net/exp4j/license.html" -- Apache License 2.0
T}
.TE
