(* Content-type: application/vnd.wolfram.cdf.text *)

(*** Wolfram CDF File ***)
(* http://www.wolfram.com/cdf *)

(* CreatedBy='Mathematica 11.0' *)

(*************************************************************************)
(*                                                                       *)
(*  The Mathematica License under which this file was created prohibits  *)
(*  restricting third parties in receipt of this file from republishing  *)
(*  or redistributing it by any means, including but not limited to      *)
(*  rights management or terms of use, without the express consent of    *)
(*  Wolfram Research, Inc. For additional information concerning CDF     *)
(*  licensing and redistribution see:                                    *)
(*                                                                       *)
(*        www.wolfram.com/cdf/adopting-cdf/licensing-options.html        *)
(*                                                                       *)
(*************************************************************************)

(*CacheID: 234*)
(* Internal cache information:
NotebookFileLineBreakTest
NotebookFileLineBreakTest
NotebookDataPosition[      1064,         20]
NotebookDataLength[      5950,        183]
NotebookOptionsPosition[      6249,        169]
NotebookOutlinePosition[      6769,        192]
CellTagsIndexPosition[      6726,        189]
WindowFrame->Normal*)

(* Beginning of Notebook Content *)
Notebook[{
Cell[BoxData[
 RowBox[{
  RowBox[{"ClearAll", "[", "\"\<Global`*\>\"", "]"}], ";"}]], "Input", \
"PluginEmbeddedContent"],

Cell[BoxData[
 RowBox[{
  RowBox[{"(*", " ", 
   RowBox[{
    RowBox[{"Generates", " ", "the", " ", "K"}], "-", 
    RowBox[{"Fibonacci", " ", 
     RowBox[{"matrix", "."}]}]}], " ", "*)"}], "\[IndentingNewLine]", 
  RowBox[{
   RowBox[{
    RowBox[{
     RowBox[{"GenFibMat", "[", "K_", "]"}], " ", ":=", " ", 
     RowBox[{"Join", "[", "\[IndentingNewLine]", 
      RowBox[{
       RowBox[{"{", 
        RowBox[{"Table", "[", 
         RowBox[{"1", ",", 
          RowBox[{"{", 
           RowBox[{"i", ",", "1", ",", "K"}], "}"}]}], "]"}], "}"}], ",", 
       "\[IndentingNewLine]", 
       RowBox[{"Table", "[", 
        RowBox[{
         RowBox[{"Normal", "[", 
          RowBox[{"SparseArray", "[", 
           RowBox[{
            RowBox[{"{", 
             RowBox[{"i", "\[Rule]", "1"}], "}"}], ",", " ", "K", ",", " ", 
            "0"}], "]"}], "]"}], ",", 
         RowBox[{"{", 
          RowBox[{"i", ",", "1", ",", 
           RowBox[{"K", "-", "1"}]}], "}"}]}], "]"}]}], "\[IndentingNewLine]",
       "]"}]}], ";"}], "\[IndentingNewLine]", 
   RowBox[{
    RowBox[{
     RowBox[{"KFib", "[", 
      RowBox[{"N_", ",", " ", "K_"}], "]"}], ":=", 
     RowBox[{
      RowBox[{"MatrixPower", "[", 
       RowBox[{
        RowBox[{"GenFibMat", "[", "K", "]"}], ",", "N"}], "]"}], ".", 
      RowBox[{"Transpose", "[", 
       RowBox[{"{", 
        RowBox[{"Normal", "[", 
         RowBox[{"SparseArray", "[", 
          RowBox[{
           RowBox[{"{", 
            RowBox[{
             RowBox[{"1", "\[Rule]", "1"}], ",", 
             RowBox[{"2", "\[Rule]", "1"}]}], "}"}], ",", "K"}], "]"}], "]"}],
         "}"}], "]"}]}]}], ";"}]}]}]], "Input", "PluginEmbeddedContent"],

Cell[BoxData[
 RowBox[{
  RowBox[{"Manipulate", "[", 
   RowBox[{
    RowBox[{"MatrixForm", "[", 
     RowBox[{"GenFibMat", "[", "K", "]"}], "]"}], ",", 
    RowBox[{"{", 
     RowBox[{"K", ",", " ", "1", ",", " ", "10", ",", "1"}], "}"}]}], "]"}], 
  ";"}]], "Input", "PluginEmbeddedContent"],

Cell[CellGroupData[{

Cell[BoxData[
 RowBox[{"Manipulate", "[", 
  RowBox[{
   RowBox[{"Row", "[", 
    RowBox[{"{", 
     RowBox[{
      RowBox[{"MatrixForm", "[", 
       RowBox[{"GenFibMat", "[", "K", "]"}], "]"}], ",", 
      RowBox[{"MatrixForm", "[", 
       RowBox[{"KFib", "[", 
        RowBox[{
         RowBox[{"N", "-", "1"}], ",", "K"}], "]"}], "]"}], ",", "\"\<=\>\"", 
      ",", 
      RowBox[{"MatrixForm", "[", 
       RowBox[{"KFib", "[", 
        RowBox[{"N", ",", "K"}], "]"}], "]"}]}], "}"}], "]"}], ",", 
   RowBox[{"{", 
    RowBox[{"N", ",", "1", ",", "10", ",", "1"}], "}"}], ",", 
   RowBox[{"{", 
    RowBox[{"K", ",", "2", ",", "5", ",", "1"}], "}"}], ",", 
   RowBox[{"Deployed", "\[Rule]", "True"}], ",", 
   RowBox[{"ContentSize", "\[Rule]", "Full"}]}], "]"}]], "Input", \
"PluginEmbeddedContent",
 NumberMarks->False],

Cell[BoxData[
 TagBox[
  StyleBox[
   DynamicModuleBox[{K$$ = 2, N$$ = 1, Typeset`show$$ = True, 
    Typeset`bookmarkList$$ = {}, Typeset`bookmarkMode$$ = "Menu", 
    Typeset`animator$$, Typeset`animvar$$ = 1, Typeset`name$$ = 
    "\"untitled\"", Typeset`specs$$ = {{
      Hold[N$$], 1, 10, 1}, {
      Hold[K$$], 2, 5, 1}}, Typeset`size$$ = {
    115., {11.634033203125, 17.365966796875}}, Typeset`update$$ = 0, 
    Typeset`initDone$$, Typeset`skipInitDone$$ = True, N$34592$$ = 0, 
    K$34593$$ = 0}, 
    DynamicBox[Manipulate`ManipulateBoxes[
     1, StandardForm, "Variables" :> {K$$ = 2, N$$ = 1}, 
      "ControllerVariables" :> {
        Hold[N$$, N$34592$$, 0], 
        Hold[K$$, K$34593$$, 0]}, 
      "OtherVariables" :> {
       Typeset`show$$, Typeset`bookmarkList$$, Typeset`bookmarkMode$$, 
        Typeset`animator$$, Typeset`animvar$$, Typeset`name$$, 
        Typeset`specs$$, Typeset`size$$, Typeset`update$$, Typeset`initDone$$,
         Typeset`skipInitDone$$}, "Body" :> Row[{
         MatrixForm[
          $CellContext`GenFibMat[K$$]], 
         MatrixForm[
          $CellContext`KFib[N$$ - 1, K$$]], "=", 
         MatrixForm[
          $CellContext`KFib[N$$, K$$]]}], 
      "Specifications" :> {{N$$, 1, 10, 1}, {K$$, 2, 5, 1}}, 
      "Options" :> {Deployed -> True, ContentSize -> Full}, 
      "DefaultOptions" :> {}],
     ImageSizeCache->{717., {72., 78.}},
     SingleEvaluation->True],
    Deinitialization:>None,
    DynamicModuleValues:>{},
    SynchronousInitialization->True,
    UndoTrackedVariables:>{Typeset`show$$, Typeset`bookmarkMode$$},
    UnsavedVariables:>{Typeset`initDone$$},
    UntrackedVariables:>{Typeset`size$$}], "Manipulate",
   Deployed->True,
   StripOnInput->False],
  Manipulate`InterpretManipulate[1]]], "Output", "PluginEmbeddedContent"]
}, Open  ]]
},
WindowSize->{739.88, 394.56},
Visible->True,
AuthoredSize->{740, 395},
ScrollingOptions->{"HorizontalScrollRange"->Fit,
"VerticalScrollRange"->Fit},
ShowCellBracket->False,
Deployed->True,
CellContext->Notebook,
TrackCellChangeTimes->False,
FrontEndVersion->"11.0 for Mac OS X x86 (32-bit, 64-bit Kernel) (July 28, \
2016)",
StyleDefinitions->"Default.nb"
]
(* End of Notebook Content *)

(* Internal cache information *)
(*CellTagsOutline
CellTagsIndex->{}
*)
(*CellTagsIndex
CellTagsIndex->{}
*)
(*NotebookFileOutline
Notebook[{
Cell[1464, 33, 121, 3, 17, "Input"],
Cell[1588, 38, 1686, 48, 144, "Input"],
Cell[3277, 88, 293, 8, 17, "Input"],
Cell[CellGroupData[{
Cell[3595, 100, 827, 23, 60, "Input"],
Cell[4425, 125, 1808, 41, 152, "Output"]
}, Open  ]]
}
]
*)

(* End of internal cache information *)

(* NotebookSignature svTn8wFTepY9eDK#TxzrJAXA *)
