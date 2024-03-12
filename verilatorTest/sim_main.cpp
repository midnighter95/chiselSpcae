  #include "Vadder.h"
  #include "verilated.h"
  #include "verilated_fst_c.h"

  vluint64_t main_time = 0;  //initial 仿真时间

  double sc_time_stamp()
 {
     return main_time;
 }


  int main(int argc, char** argv) {
      VerilatedContext* contextp = new VerilatedContext;
      contextp->commandArgs(argc, argv);
      Verilated::traceEverOn(true); //导出vcd波形需要加此语句
      VerilatedFstC* tfp = new VerilatedFstC;

      //top type is Vour, instaniate here.
      Vadder* top = new Vadder{contextp};

      top->trace(tfp, 0);
      tfp->open("wave.fst"); //打开vcd
      while (main_time < 200000 && !Verilated::gotFinish()) {
         int a = rand() % 127;
         int b = rand() % 127;
         int cin = rand() % 1;
         top->a = a;
         top->b = b;
         top->cin = cin;
         top->eval();
         printf("a = %d, b = %d, cin=%d, Sum = %d, Carry= %d\n", a, b, cin, top->z, top->cout);
         printf("time = %d", main_time);
         assert(top->z + (top->cout)*128 == (a + b + cin));
         top->eval();
         tfp->dump(main_time); //dump wave
         main_time++; //推动仿真时间
      }
     top->final();
     tfp->close();
     delete top;
     return 0;
  }
