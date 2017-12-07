'use strict';
module.exports = function (grunt) {

    require('load-grunt-tasks')(grunt); // npm install --save-dev load-grunt-tasks

    grunt.initConfig({
        babel: {
            compile: {
                options: {
                    sourceMap: true,
                    presets: ['env']
                },
                files: {
                    'js/tmp/toDoListController-compiled.js': 'js/toDoListController.js'
                }
            }
        },
        jshint: {
            all: ['Gruntfile.js', 'js/**/*.js']
        },
        watch: {
            files: [ 'js/**/*.js'],
            tasks: ['jshint']
        }
    });

    grunt.loadTasks('tasks');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.registerTask('default', ['babel', 'clean', 'jshint', 'watch']);

};