/* Add here all your JS customizations */

function cliquerSearche(){

}

document.getElementById("profile_image").onchange = function() {
    document.getElementById("image_form").submit();
};

$("#span_searche").click(function() {
    $("#btn_searche").click();
});

$(".code_afiche .btn_edit").click(function() {
    $(".code_afiche").hide();
    $(".code_edit").show();
});

$(".code_edit .btn_annuler").click(function() {
    $(".code_edit").hide();
    $(".code_afiche").show();
});

// supprimer un objet dans la base de donnes
$(".btn_supp").click(function (e) {
    e.preventDefault();
    $("#supp_modal .submit_supp").attr("href", $(this).attr("href"));
    $("#supp_modal").modal();
});


$.get("http://localhost:8087/api/notifNumber", function(data) {
        $(".notif_span").html(data);
});

$.get("http://localhost:8087/api/projetNumber", function(data) {
    $(".nbr_projet_span").html(data);
});

// supprimer les notifs après remplissage de toutes les phases
$.get("http://localhost:8087/api/projetList", function(data) {
    $.each(data, function(i, elem) {
        if(
            elem.pjointes && elem.pjointes.phase1 && elem.pjointes.phase2 && elem.pjointes.phase3 && elem.pjointes.phase4 &&

            elem.pjointes.phase2.releve_existant && elem.pjointes.phase2.photo_site &&
            elem.pjointes.phase2.esquisse && elem.pjointes.phase2.plan_dwg &&

            elem.pjointes.phase3.autorization && elem.pjointes.phase3.pv_commition && elem.pjointes.phase3.plan_approuve &&
            elem.pjointes.phase3.attestation_impl && elem.pjointes.phase3.plan_beton_arme &&
            elem.pjointes.phase3.pv_suivi && elem.pjointes.phase3.photo_exec &&

            elem.pjointes.phase4.photo_acheve &&  elem.pjointes.phase4.fermeture_chantier &&
            elem.pjointes.phase4.attestation_fin_travaux

        ){
            $.get("http://localhost:8087/api/"+elem.ref+"/notif/delete", function(data) { });
        }

    });
});

// Notifier apres 15 jours
$.get("http://localhost:8087/api/projetList", function(data) {
    $.each(data, function(i, elem) {
        var date1= Date.parse(elem.ficheProjet.date_entree);
        var date2= new Date();
        var date= date2 - date1;

        var minutes = 1000 * 60; var hours = minutes * 60; var days = hours * 24;
        var m= Math.round(date / minutes);
        var h= Math.round(date / hours);
        var d= Math.round(date / days);

            if(d >= 15 &&
                (!elem.pjointes || !elem.pjointes.phase1 || !elem.pjointes.phase2 || !elem.pjointes.phase3 || !elem.pjointes.phase4 ||

                 !elem.pjointes.phase2.releve_existant || !elem.pjointes.phase2.photo_site ||
                 !elem.pjointes.phase2.esquisse || !elem.pjointes.phase2.plan_dwg ||

                 !elem.pjointes.phase3.autorization || !elem.pjointes.phase3.pv_commition || !elem.pjointes.phase3.plan_approuve ||
                 !elem.pjointes.phase3.attestation_impl || !elem.pjointes.phase3.plan_beton_arme ||
                 !elem.pjointes.phase3.pv_suivi || !elem.pjointes.phase3.photo_exec ||

                !elem.pjointes.phase4.photo_acheve ||  !elem.pjointes.phase4.fermeture_chantier ||
                !elem.pjointes.phase4.attestation_fin_travaux
                )
                ){
                $.get("http://localhost:8087/api/"+elem.ref+"/notif", function(data) { });
            }

    });
});

// notifier apres reported (15 jours de plus)
$.get("http://localhost:8087/api/notif_reported_list", function(data) {
    $.each(data, function(i, elem) {
        var date1= Date.parse(elem.report_date);
        var date2= new Date();
        var date= date2 - date1;
        var minutes = 1000 * 60; var hours = minutes * 60; var days = hours * 24;
        var m= Math.round(date / minutes);
        var h= Math.round(date / hours);
        var d= Math.round(date / days);

        if(d >= 15 &&
            (!elem.pjointes || !elem.pjointes.phase1 || !elem.pjointes.phase2 || !elem.pjointes.phase3 || !elem.pjointes.phase4 ||

                !elem.pjointes.phase2.releve_existant || !elem.pjointes.phase2.photo_site ||
                !elem.pjointes.phase2.esquisse || !elem.pjointes.phase2.plan_dwg ||

                !elem.pjointes.phase3.autorization || !elem.pjointes.phase3.pv_commition || !elem.pjointes.phase3.plan_approuve ||
                !elem.pjointes.phase3.attestation_impl || !elem.pjointes.phase3.plan_beton_arme ||
                !elem.pjointes.phase3.pv_suivi || !elem.pjointes.phase3.photo_exec ||

                !elem.pjointes.phase4.photo_acheve ||  !elem.pjointes.phase4.fermeture_chantier ||
                !elem.pjointes.phase4.attestation_fin_travaux
            )
        ){
            $.get("http://localhost:8087/api/"+elem.id+"/notif/reporter", function(data) { });
        }
        if(!elem.disabled){
            var dateM= Date.parse(elem.date);
            var dateR= date2 - dateM;
            var mr= Math.round(dateR / minutes);
            var hr= Math.round(dateR / hours);
            var dr= Math.round(dateR / days);
            if(mr < 60){
                var notifClass= ".notif_rp_"+elem.id;
                $(notifClass).html("il y a "+mr+" minutes");
            }
            else if(hr>0 && dr<=0){
                var notifClass= ".notif_rp_"+elem.id;
                $(notifClass).html("il y a "+hr+" heurs");
            }
            else {
                var notifClass= ".notif_rp_"+elem.id;
                $(notifClass).html("il y a "+dr+" jours");
            }
        }


    });
});


// afficher les notifs dans le header
$.get("http://localhost:8087/api/notifList", function(data) {
    $.each(data, function(i, elem) {
            if(i == 4){return;}
            if(!elem.disabled){
                var date1= Date.parse(elem.date);
                var date2= new Date();
                var date= date2 - date1;
                var minutes = 1000 * 60; var hours = minutes * 60; var days = hours * 24;
                var m= Math.round(date / minutes);
                var h= Math.round(date / hours);
                var d= Math.round(date / days);
                if(m < 60){
                    $(".notif_ls").append(
                        "<a th:href='@{/notifs}' class=\"clearfix\">\n" +
                        "            <div class=\"image\">\n" +
                        "            <i class=\"fa fa-warning \"></i>\n" +
                        "            </div>\n" +
                        "            <span class=\"notif_text title\">Vous avez un projet en suspension! ("+elem.projetid+")</span>\n" +
                        "        <span class=\"notif_time message\">il y a "+m+" minutes</span>\n" +
                        "        </a>"
                    );
                    var notifClass= ".notif_"+elem.id;
                    $(notifClass).html("il y a "+m+" minutes");
                }
                else if(h>0 && d<=0){
                    $(".notif_ls").append(
                        "<a th:href='@{/notifs}' class=\"clearfix\">\n" +
                        "            <div class=\"image\">\n" +
                        "            <i class=\"fa fa-warning \"></i>\n" +
                        "            </div>\n" +
                        "            <span class=\"notif_text title\">Vous avez un projet en suspension! ("+elem.projetid+")</span>\n" +
                        "        <span class=\"notif_time message\">il y a "+h+" heurs</span>\n" +
                        "        </a>"
                    );
                    var notifClass= ".notif_"+elem.id;
                    $(notifClass).html("il y a "+h+" heurs");
                }
                else {
                    $(".notif_ls").append(
                        "<a th:href='@{/notifs}' class=\"clearfix\">\n" +
                        "            <div class=\"image\">\n" +
                        "            <i class=\"fa fa-warning \"></i>\n" +
                        "            </div>\n" +
                        "            <span class=\"notif_text title\">Vous avez un projet en suspension! ("+elem.projetid+")</span>\n" +
                        "        <span class=\"notif_time message\">il y a "+d+" jours</span>\n" +
                        "        </a>"
                    );
                    var notifClass= ".notif_"+elem.id;
                    $(notifClass).html("il y a "+d+" jours");
                }
            }
    });
});
