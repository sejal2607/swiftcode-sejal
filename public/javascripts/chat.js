var app = angular.module('chatApp', ['ngMaterial']);

app.config(function ($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('purple')
        .accentPalette('');
});

app.controller('chatController', function ($scope) {
    $scope.messages = [
        {
            'sender': 'USER',
            'text': 'Hello'

    },
        {
            'sender': 'BOT',
            'text': 'hii what i can do for u??'
    },
        {
            'sender': 'USER',
            'text': 'help me in solving problem!!'
   },
        {
            'sender': 'BOT',
            'text': 'what u want me to help in'
}
    ];

});