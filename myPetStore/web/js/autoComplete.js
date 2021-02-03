$( function() {
    $.widget( "custom.catcomplete", $.ui.autocomplete, {
        _create: function() {
            this._super();
            this.widget().menu( "option", "items", "> :not(.ui-autocomplete-category)" );
        },
        _renderMenu: function( ul, items ) {
            var that = this,
                currentCategory = "";
            $.each( items, function( index, item ) {
                var li;
                if ( item.category !== currentCategory ) {
                    ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
                    currentCategory = item.category;
                }
                li = that._renderItemData( ul, item );
                if ( item.category ) {
                    li.attr( "aria-label", item.category + " : " + item.label );
                }
            });
        }
    });
    var data = [
        { label: "Koi", category: "Fish" },
        { label: "Goldfish", category: "Fish" },
        { label: "Angelfish", category: "Fish" },
        { label: "Tiger Shark", category: "Fish" },
        { label: "Bulldog", category: "Dogs" },
        { label: "Chihuahua", category: "Dogs" },
        { label: "Dalmation", category: "Dogs" },
        { label: "Poodle", category: "Dogs" },
        { label: "Golden Retriever", category: "Dogs" },
        { label: "Labrador Retriever", category: "Dogs" },
        { label: "Persian", category: "Cats" },
        { label: "Manx", category: "Cats" },
        { label: "Iguana", category: "Reptiles" },
        { label: "Rattlesnake", category: "Reptiles" },
        { label: "Amazon Parrot", category: "Birds" },
        { label: "Finch", category: "Birds" }
    ];

    $( "#searchKeyword" ).catcomplete({
        delay: 0,
        source: data
    });
} )