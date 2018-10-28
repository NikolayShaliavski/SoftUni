using Microsoft.EntityFrameworkCore.Migrations;

namespace SocialNetworkData.Migrations
{
    public partial class SharedAlbums : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "UserAlbums",
                columns: table => new
                {
                    UserId = table.Column<int>(nullable: false),
                    AlbumId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_UserAlbums", x => new { x.UserId, x.AlbumId });
                    table.ForeignKey(
                        name: "FK_UserAlbums_ALbums_AlbumId",
                        column: x => x.AlbumId,
                        principalTable: "ALbums",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_UserAlbums_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateIndex(
                name: "IX_UserAlbums_AlbumId",
                table: "UserAlbums",
                column: "AlbumId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "UserAlbums");
        }
    }
}
