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

exampletest:
	mill -i -j 0 examples.tests

run:
	mill -i -j 0 playground[6.0.0]

test:
	mill -i -j 0 playgroundtest[6.0.0]
