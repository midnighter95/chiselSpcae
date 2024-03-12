module TwoCombineG(
  input  g0,
         p1,
         g1,
  output gOut
);

  assign gOut = g0 & p1 | g1;
endmodule
