/* Add here all your JS customizations */

$(".code_afiche .btn_edit").click(function() {
    $(".code_afiche").hide();
    $(".code_edit").show();
});

$(".code_edit .btn_annuler").click(function() {
    $(".code_edit").hide();
    $(".code_afiche").show();
});
