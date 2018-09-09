
var Position = class Position {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }
};

var Branch = class Branch {

     constructor(id, position) {
        this.id = id;
        this.position = position;
        this.commitMap = {};
        this.color = "#666666";
    }

    addCommit(commit){
         this.commitMap.id = { id: commit.id, obj: commit, pos: new Position(this.position.x, this.position.y)};

         cy.add([
              { group: "nodes", data: { id:commit.id }, position: this.position , style :  { "background-color" :this.color} }
         ]);

         if(this.lastCommitAdded){
            cy.add([
                {
                    group: "edges",
                    data:
                    {
                           id:  this.buildEdgeId(this.lastCommitAdded,commit) ,
                           source:this.lastCommitAdded.id ,
                           target: commit.id
                    },
                    style : { "line-color" :  this.color }

                }
            ]);
         }

         this.position = new Position( this.position.x, this.position.y + 50);
         this.lastCommitAdded = commit;
    }

    buildEdgeId(commit1,commit2){
        return this.id+"-"+commit1.id+"-"+commit2.id;
    }

    addBranch(branch){
        branch.position.x = this.position.x + 100;
        branch.position.y = this.position.y;
        branch.lastCommitAdded = this.lastCommitAdded;
    }

};
// Create branches
var master = new Branch("master",new Position(50,50));
var devBranch = new Branch("devBranch",new Position(0,0));
devBranch.color = "yellow"

// Add commit to branches
master.addCommit(new Commit("Commit1", "My commit 1"));
master.addCommit(new Commit("commit2", "My commit 1"));
master.addCommit(new Commit("commit3", "My commit 1"));
master.addCommit(new Commit("commit4", "My commit 1"));
master.addBranch(devBranch);
master.addCommit(new Commit("commit5", "My commit 1"));
master.addCommit(new Commit("commit6", "My commit 1"));

devBranch.addCommit(new Commit("Commit7", "My commit 1"));
devBranch.addCommit(new Commit("commit8", "My commit 1"));
devBranch.addCommit(new Commit("commit9", "My commit 1"));
devBranch.addCommit(new Commit("commit10", "My commit 1"));
devBranch.addCommit(new Commit("commit11", "My commit 1"));
devBranch.addCommit(new Commit("commit12", "My commit 1"));

$( document ).ready(function() {
  // Handler for .ready() called.
    var latestNodeTapped;
    var latestNodeTappedBackgroundColor = "";
    var selectedNodeColor = 'black';

    cy.on('tap', 'node', function(evt){

      // Re-set color to latest node tapped.
      if(latestNodeTapped){
        latestNodeTapped.style('background-color',latestNodeTappedBackgroundColor);
      }

      // Change color to selected node.
      var node = evt.target;
      latestNodeTapped = node;
      latestNodeTappedBackgroundColor = node.style('background-color');
      node.style('background-color',selectedNodeColor);

    });
});