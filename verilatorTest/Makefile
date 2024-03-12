verilog := $(wildcard *.v)

all:
	verilator --cc --exe --build -j 0 -Wall -Wno-fatal --trace-fst sim_main.cpp $(verilog)
	obj_dir/Vadder
