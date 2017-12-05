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
        }
    });

    grunt.loadTasks('tasks');

    grunt.registerTask('default', ['babel', 'clean']);

};