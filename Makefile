CLASSES   := GraphingCalculator GraphPanel Equation Point
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

$(JARFILE): $(addprefix $(BUILDDIR)/,$(addsuffix .class,$(CLASSES)))
	for LIB in $(CPLIBS) ; do bsdtar -C $(BUILDDIR) -xf $(LIBDIR)/exp4j-0.4.8.jar --exclude=META-INF ; done
	$(JAR) -cfe $@ $(MAINCLASS) -C $(BUILDDIR) .

$(BUILDDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) -d $(BUILDDIR) -cp $(subst $() $(),:,$(CPLIBS)):$(SRCDIR) -implicit:none $<

$(BUILDDIR):
	mkdir -p $@

.PHONY: clean
clean: clean-build clean-jar

.PHONY: clean-build
clean-build:
	rm -rf $(wildcard $(BUILDDIR)/*)
	rm -df $(BUILDDIR)

.PHONY: clean-jar
clean-jar:
	rm -f $(JARFILE)

.PHONY: run
run: $(JARFILE)
	@$(JAVA) -jar $<
