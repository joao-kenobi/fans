# fans

FANS is an abbreviation written in reverse order for (Super Nintendo Assembly Framework)


The purpose of this project is to have the ease of use and readability that high-level language IDEs have, such as refactoring methods and just hovering over a documented artifact to see the description of the registers.


With FANS you can write in java exactly as you write in assembly, in addition to using other facilities

Small example:

Assembly code:
lda #$00
it's $01

Equivalent in the framework:
lda("#$00");
sta("#$01");

OR

ldaSta("#$00", "$01");
