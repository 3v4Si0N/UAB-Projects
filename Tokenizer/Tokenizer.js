function Tokenizer() {
	this.dictionary = [];
	this.default;

	this.on = function(character, funct) {
		this.dictionary[character] = funct;
	}

	this.onDefault = function(f){
		this.default = f;
	}

	this.run = function(list) {
		var that = this;
		list.forEach(function(element) {
			if (that.dictionary[element] != undefined) {
				that.dictionary[element]();
			} else {
				that.default();
			}
		})
	}
}

function testTokenizer(){
  
  var t = new Tokenizer();
  var countA = 0;
  var countC = 0;
  var countOtros = 0;
  var testString = ['H','o','l','a',' ','c','o','m',' ','a','n','e','u','?'];


  t.on('a', function() {
  	return countA++;
  });

  t.on('c', function() {
        return countC++;
  });
  
  t.onDefault(function() {
  	return countOtros++;
  });

  t.run(testString);

  //here goes the code to run the test over testString
  
  console.log("numero de a's: " + countA);
  console.log("numero de c's: " + countC);
  console.log("numero d'altres caracters: " + countOtros);
}

testTokenizer();