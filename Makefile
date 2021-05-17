CLASSES   := GraphingCalculator GraphPanel Equation
MAINCLASS := GraphingCalculator
JARFILE   := GraphingCalculator.jar

BUILDDIR := build
SRCDIR   := src
LIBDIR   := lib

JAVAC := javac
JAR   := jar
JAVA  := java

CPLIBS := $(wildcard $(LIBDIR)/*.jar)

.PHONY: all
all: $(JARFILE)
	rm -f Manifest.txt

$(JARFILE): $(addprefix $(BUILDDIR)/,$(addsuffix .class,$(CLASSES))) Manifest.txt
	$(JAR) -cfm $@ Manifest.txt -C $(BUILDDIR) .

$(BUILDDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BUILDDIR) -cp $(subst $() $(),:,$(CPLIBS)):$(SRCDIR) -implicit:none $<

Manifest.txt:
	echo "Manifest-Version: 1.0" > $@
	echo "Class-Path: $(CPLIBS)" >> $@
	echo "Main-Class: $(MAINCLASS)" >> $@

$(BUILDDIR):
	mkdir -p $@

.PHONY: clean
clean: clean-build clean-jar

.PHONY: clean-build
clean-build:
	rm -f $(wildcard $(BUILDDIR)/*)
	rm -df $(BUILDDIR)

.PHONY: clean-jar
clean-jar:
	rm -f $(JARFILE)

.PHONY: run
run: $(JARFILE)
	@$(JAVA) -jar $<
