/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $.datepicker.setDefaults({
        dateFormat: 'dd-mm-yyyy'
    });
});


$("#submitApplication").on("click", function () {
    var start = $("#start").datepicker().val();
    var end = $("#end").datepicker({dateFormat: 'dd-mm-yyyy'}).val();
    $("#start").attr("value", start);
    $("#end").attr("value", end);
});