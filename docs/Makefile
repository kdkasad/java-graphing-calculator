SRCS := $(wildcard *.ms)
PDFS := $(SRCS:.ms=.pdf)

.PHONY: all
all: $(PDFS)

.PHONY: clean
clean:
	rm -f $(PDFS)

%.pdf: %.ms
	pdfroff -ms -mpdfmark -kpt -d paper=letter -P  -pletter $^ > $@
