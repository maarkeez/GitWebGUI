 var cy = cytoscape({

  container: $('#cy'),

  style: [ // the stylesheet for the graph
    {
      selector: 'node',
      style:
      {
        'background-color': '#666',
        'width': 17,
        'height' : 17,
        'label': 'data(id)',
        'text-halign' : 'right',
        'text-valign' : 'center',
        'font-size' : '15px'
      }
    },

    {
      selector: 'edge',
      style: {
        'width': 3,
        'line-color': '#ccc',
        'target-arrow-color': '#ccc',
        'target-arrow-shape': 'triangle'
      }
    }
  ],

  layout: {
    name: 'grid',
    rows: 1
  }

});
cy.zoom({
  level: 1.0 // the zoom level
});
cy.zoomingEnabled( false );

cy.pan({
  x: 0,
  y: 0
});


cy.autoungrabify( true );
cy.userPanningEnabled( false );
