init:
	git submodule update --init

compile:
	mill -i -j 0 playground.compile

bump:
	git submodule foreach git stash
	git submodule update --remote
	git add dependencies

bsp:
	mill -i -j 0 mill.bsp.BSP/install

clean:
	git clean -fd

reformat:
	mill -i __.reformat

checkformat:
	mill -i __.checkFormat 

test:
	mill -i -j 0 arithmetic.tests
