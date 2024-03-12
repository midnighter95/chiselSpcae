module adder(
  input        clock,
               reset,
  input  [6:0] a,
               b,
  input        cin,
  output [6:0] z,
  output       cout
);

  wire       _sum_xor_8_z;
  wire       _sum_xor_7_z;
  wire       _sum_xor_6_z;
  wire       _sum_xor_5_z;
  wire       _sum_xor_4_z;
  wire       _sum_xor_3_z;
  wire       _sum_xor_2_z;
  wire       _sum_xor_1_z;
  wire       _sum_xor_z;
  wire       _twoCombineG_11_gOut;
  wire       _twoCombineG_10_gOut;
  wire       _twoCombineG_9_gOut;
  wire       _twoCombineG_8_gOut;
  wire       _twoCombineG_7_gOut;
  wire       _twoCombineP_7_z;
  wire       _twoCombineG_6_gOut;
  wire       _twoCombineP_6_z;
  wire       _twoCombineG_5_gOut;
  wire       _twoCombineP_5_z;
  wire       _twoCombineG_4_gOut;
  wire       _twoCombineG_3_gOut;
  wire       _twoCombineP_3_z;
  wire       _twoCombineG_2_gOut;
  wire       _twoCombineP_2_z;
  wire       _twoCombineG_1_gOut;
  wire       _twoCombineP_1_z;
  wire       _twoCombineG_gOut;
  wire       _twoCombineP_z;
  wire       _and_7_z;
  wire       _xor_7_z;
  wire       _and_6_z;
  wire       _xor_6_z;
  wire       _and_5_z;
  wire       _xor_5_z;
  wire       _and_4_z;
  wire       _xor_4_z;
  wire       _and_3_z;
  wire       _xor_3_z;
  wire       _and_2_z;
  wire       _xor_2_z;
  wire       _and_1_z;
  wire       _xor_1_z;
  wire       _and_z;
  wire       _xor_z;
  wire       as_1 = a[0];
  wire       as_2 = a[1];
  wire       as_3 = a[2];
  wire       as_4 = a[3];
  wire       as_5 = a[4];
  wire       as_6 = a[5];
  wire       as_7 = a[6];
  wire       bs_1 = b[0];
  wire       bs_2 = b[1];
  wire       bs_3 = b[2];
  wire       bs_4 = b[3];
  wire       bs_5 = b[4];
  wire       bs_6 = b[5];
  wire       bs_7 = b[6];
  wire [1:0] s_lo_lo = {_sum_xor_1_z, _sum_xor_z};
  wire [1:0] s_lo_hi = {_sum_xor_3_z, _sum_xor_2_z};
  wire [3:0] s_lo = {s_lo_hi, s_lo_lo};
  wire [1:0] s_hi_lo = {_sum_xor_5_z, _sum_xor_4_z};
  wire [1:0] s_hi_hi_hi = {_sum_xor_8_z, _sum_xor_7_z};
  wire [2:0] s_hi_hi = {s_hi_hi_hi, _sum_xor_6_z};
  wire [4:0] s_hi = {s_hi_hi, s_hi_lo};
  wire [8:0] s = {s_hi, s_lo};
  Xor xor_0 (
    .a (cin),
    .b (cin),
    .z (_xor_z)
  );
  And and_0 (
    .a (cin),
    .b (cin),
    .z (_and_z)
  );
  Xor xor_1 (
    .a (as_1),
    .b (bs_1),
    .z (_xor_1_z)
  );
  And and_1 (
    .a (as_1),
    .b (bs_1),
    .z (_and_1_z)
  );
  Xor xor_2 (
    .a (as_2),
    .b (bs_2),
    .z (_xor_2_z)
  );
  And and_2 (
    .a (as_2),
    .b (bs_2),
    .z (_and_2_z)
  );
  Xor xor_3 (
    .a (as_3),
    .b (bs_3),
    .z (_xor_3_z)
  );
  And and_3 (
    .a (as_3),
    .b (bs_3),
    .z (_and_3_z)
  );
  Xor xor_4 (
    .a (as_4),
    .b (bs_4),
    .z (_xor_4_z)
  );
  And and_4 (
    .a (as_4),
    .b (bs_4),
    .z (_and_4_z)
  );
  Xor xor_5 (
    .a (as_5),
    .b (bs_5),
    .z (_xor_5_z)
  );
  And and_5 (
    .a (as_5),
    .b (bs_5),
    .z (_and_5_z)
  );
  Xor xor_6 (
    .a (as_6),
    .b (bs_6),
    .z (_xor_6_z)
  );
  And and_6 (
    .a (as_6),
    .b (bs_6),
    .z (_and_6_z)
  );
  Xor xor_7 (
    .a (as_7),
    .b (bs_7),
    .z (_xor_7_z)
  );
  And and_7 (
    .a (as_7),
    .b (bs_7),
    .z (_and_7_z)
  );
  And twoCombineP (
    .a (_xor_z),
    .b (_xor_1_z),
    .z (_twoCombineP_z)
  );
  TwoCombineG twoCombineG (
    .g0   (_and_z),
    .p1   (_xor_1_z),
    .g1   (_and_1_z),
    .gOut (_twoCombineG_gOut)
  );
  And twoCombineP_1 (
    .a (_xor_2_z),
    .b (_xor_3_z),
    .z (_twoCombineP_1_z)
  );
  TwoCombineG twoCombineG_1 (
    .g0   (_and_2_z),
    .p1   (_xor_3_z),
    .g1   (_and_3_z),
    .gOut (_twoCombineG_1_gOut)
  );
  And twoCombineP_2 (
    .a (_xor_4_z),
    .b (_xor_5_z),
    .z (_twoCombineP_2_z)
  );
  TwoCombineG twoCombineG_2 (
    .g0   (_and_4_z),
    .p1   (_xor_5_z),
    .g1   (_and_5_z),
    .gOut (_twoCombineG_2_gOut)
  );
  And twoCombineP_3 (
    .a (_xor_6_z),
    .b (_xor_7_z),
    .z (_twoCombineP_3_z)
  );
  TwoCombineG twoCombineG_3 (
    .g0   (_and_6_z),
    .p1   (_xor_7_z),
    .g1   (_and_7_z),
    .gOut (_twoCombineG_3_gOut)
  );
  And twoCombineP_4 (
    .a (_twoCombineP_z),
    .b (_xor_2_z),
    .z (/* unused */)
  );
  TwoCombineG twoCombineG_4 (
    .g0   (_twoCombineG_gOut),
    .p1   (_xor_2_z),
    .g1   (_and_2_z),
    .gOut (_twoCombineG_4_gOut)
  );
  And twoCombineP_5 (
    .a (_twoCombineP_z),
    .b (_twoCombineP_1_z),
    .z (_twoCombineP_5_z)
  );
  TwoCombineG twoCombineG_5 (
    .g0   (_twoCombineG_gOut),
    .p1   (_twoCombineP_1_z),
    .g1   (_twoCombineG_1_gOut),
    .gOut (_twoCombineG_5_gOut)
  );
  And twoCombineP_6 (
    .a (_twoCombineP_2_z),
    .b (_xor_6_z),
    .z (_twoCombineP_6_z)
  );
  TwoCombineG twoCombineG_6 (
    .g0   (_twoCombineG_2_gOut),
    .p1   (_xor_6_z),
    .g1   (_and_6_z),
    .gOut (_twoCombineG_6_gOut)
  );
  And twoCombineP_7 (
    .a (_twoCombineP_2_z),
    .b (_twoCombineP_3_z),
    .z (_twoCombineP_7_z)
  );
  TwoCombineG twoCombineG_7 (
    .g0   (_twoCombineG_2_gOut),
    .p1   (_twoCombineP_3_z),
    .g1   (_twoCombineG_3_gOut),
    .gOut (_twoCombineG_7_gOut)
  );
  And twoCombineP_8 (
    .a (_twoCombineP_5_z),
    .b (_xor_4_z),
    .z (/* unused */)
  );
  TwoCombineG twoCombineG_8 (
    .g0   (_twoCombineG_5_gOut),
    .p1   (_xor_4_z),
    .g1   (_and_4_z),
    .gOut (_twoCombineG_8_gOut)
  );
  And twoCombineP_9 (
    .a (_twoCombineP_5_z),
    .b (_twoCombineP_2_z),
    .z (/* unused */)
  );
  TwoCombineG twoCombineG_9 (
    .g0   (_twoCombineG_5_gOut),
    .p1   (_twoCombineP_2_z),
    .g1   (_twoCombineG_2_gOut),
    .gOut (_twoCombineG_9_gOut)
  );
  And twoCombineP_10 (
    .a (_twoCombineP_5_z),
    .b (_twoCombineP_6_z),
    .z (/* unused */)
  );
  TwoCombineG twoCombineG_10 (
    .g0   (_twoCombineG_5_gOut),
    .p1   (_twoCombineP_6_z),
    .g1   (_twoCombineG_6_gOut),
    .gOut (_twoCombineG_10_gOut)
  );
  And twoCombineP_11 (
    .a (_twoCombineP_5_z),
    .b (_twoCombineP_7_z),
    .z (/* unused */)
  );
  TwoCombineG twoCombineG_11 (
    .g0   (_twoCombineG_5_gOut),
    .p1   (_twoCombineP_7_z),
    .g1   (_twoCombineG_7_gOut),
    .gOut (_twoCombineG_11_gOut)
  );
  Xor sum_xor (
    .a (_xor_z),
    .b (1'h0),
    .z (_sum_xor_z)
  );
  Xor sum_xor_1 (
    .a (_xor_1_z),
    .b (_and_z),
    .z (z[0])
  );
  Xor sum_xor_2 (
    .a (_xor_2_z),
    .b (_twoCombineG_gOut),
    .z (z[1])
  );
  Xor sum_xor_3 (
    .a (_xor_3_z),
    .b (_twoCombineG_4_gOut),
    .z (z[2])
  );
  Xor sum_xor_4 (
    .a (_xor_4_z),
    .b (_twoCombineG_5_gOut),
    .z (z[3])
  );
  Xor sum_xor_5 (
    .a (_xor_5_z),
    .b (_twoCombineG_8_gOut),
    .z (z[4])
  );
  Xor sum_xor_6 (
    .a (_xor_6_z),
    .b (_twoCombineG_9_gOut),
    .z (z[5])
  );
  Xor sum_xor_7 (
    .a (_xor_7_z),
    .b (_twoCombineG_10_gOut),
    .z (z[6])
  );
  Xor sum_xor_8 (
    .a (1'h0),
    .b (_twoCombineG_11_gOut),
    .z (cout)
  );
endmodule
